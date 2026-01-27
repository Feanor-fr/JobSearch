package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.update.UserForUpdateDto;
import kg.attractor.job_search.enums.RoleType;
import kg.attractor.job_search.exceptions.UserAlreadyExistsException;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.exceptions.WrongRoleException;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.repository.UserRepository;
import kg.attractor.job_search.service.ImageService;
import kg.attractor.job_search.service.RoleService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;
    private final RoleService roleService;



    @Override
    public void create(UserDto userDto) {

        if (!userDto.getRole().equals(RoleType.APPLICANT.getId()) &&
                !userDto.getRole().equals(RoleType.EMPLOYER.getId())) {
            throw new WrongRoleException("Аккаунт может быть только двух типов: applicant или employer");
        }


        if (userRepository.existsById(userDto.getEmail())) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует!");
        }

        User user = ConvertHelper.convertToUser(userDto);
        user.setRole(roleService.findRoleById(userDto.getRole()));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

       userRepository.save(user);
    }

    @Override
    public void update(String email, UserForUpdateDto userDto){
        User user = userRepository.findById(email)
                .orElseThrow(UserNotFoundException ::new);


        if (userDto.getEmail() != null
                && !userDto.getEmail().equalsIgnoreCase(email)) {

            if (userRepository.existsById(userDto.getEmail())) {
                throw new UserAlreadyExistsException("Этот email уже занят!");
            }

        }

        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        } else {
            userDto.setPassword(user.getPassword());
        }
        ConvertHelper.correctUserForUpdate(user, userDto);

        userRepository.save(user);

    }

    @Override
    public void photoForProfile(MultipartFile file, String email){

        User user = userRepository.findById(email)
                .orElseThrow(UserNotFoundException ::new);

        String oldAva = user.getAvatar();

        String newAva = imageService.saveUploadedFile(file, "images");

        user.setAvatar(newAva);
        imageService.deleteFile(oldAva, "images");

        userRepository.save(user);
    }

    @Override
    public List<UserDto> showAllUsersWhoResponded(Integer vacancyId) {

        return userRepository.findUsersWhoResponded(vacancyId)
                .stream()
                .map(ConvertHelper::convertToUserDto)
                .toList();
    }

    @Override
    public UserDto findApplicant(String email) {
        User user = userRepository.findById(email)
                .orElseThrow(UserNotFoundException ::new);

        if (!user.getRole().getId().equals(RoleType.APPLICANT.getId())) {
            throw new WrongRoleException("Пользователь не является соискателем");
        }

        return  ConvertHelper.convertToUserDto(user);
    }

    @Override
    public UserDto findEmployer(String email) {
        User user = userRepository.findById(email)
                .orElseThrow(UserNotFoundException ::new);

        if (!user.getRole().getId().equals(RoleType.EMPLOYER.getId())) {
            throw new WrongRoleException("Пользователь не является работодателем");
        }

       return ConvertHelper.convertToUserDto(user);
    }

    @Override
    public List<UserDto> getUserByName(String name) {

        return userRepository.findUserByName(name)
                .stream()
                .map(ConvertHelper::convertToUserDto)
                .toList();

    }

    @Override
    public List<UserDto> getUserByPhoneNumber(String phoneNumber) {

        return userRepository.findUsersByPhoneNumberContaining(phoneNumber)
                .stream()
                .map(ConvertHelper::convertToUserDto)
                .toList();
    }

    @Override
    public UserDto getUserByEmail(String email) {

        return userRepository.findById(email)
                .map(ConvertHelper::convertToUserDto)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserForUpdateDto getUserByEmailForUpdate(String email) {

        return userRepository.findById(email)
                .map(ConvertHelper::convertToUserForUpdateDto)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Boolean isUserAuthorized(String email) {
        return userRepository.existsById(email);
    }

    @Override
    public Page<UserDto> showAllUserEmployer(Integer page, Integer size){
        Pageable pageable = PageRequest.of(
                page,
                size
        );

        return userRepository
                .findUsersByRole_RoleName("Employer", pageable)
                .map(ConvertHelper::convertToUserDto);
    }

}
