package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.UserDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.enums.RoleType;
import kg.attractor.job_search.exceptions.UserAlreadyExistsException;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.exceptions.WrongRoleException;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;


    @Override
    public void create(UserDto userDto) {

        if (!userDto.getRole().equals(RoleType.APPLICANT.getId()) &&
                !userDto.getRole().equals(RoleType.EMPLOYER.getId())) {
            throw new WrongRoleException("Аккаунт может быть только двух типов: applicant или employer");
        }

        User user = convertToUser(userDto);
        List<User> users = userDao.getAllUsers();

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                throw new UserAlreadyExistsException("Пользователь с таким email уже существует!");
            }
        }

        userDao.save(user);
    }

    @Override
    public void update(String email, UserDto userDto){
        User user = userDao.findOneUserByEmail(email)
                .orElseThrow(UserNotFoundException ::new);

        if (user.getRole().equals(userDto.getRole())) {
            throw new WrongRoleException("Тип аккаунта изменить нельзя!");
        }

        if (!userDto.getEmail().equalsIgnoreCase(email)) {
            List<User> usersForCheckEmail = userDao.getUserByEmail(userDto.getEmail()).orElse(List.of());
            if (!usersForCheckEmail.isEmpty()) {
                throw new UserAlreadyExistsException("Этот email уже занят!");
            }
        }

        User check = convertToUser(userDto);
        userDao.update(email, correctUserForDao(user, check));

    }

    @Override
    public List<UserDto> showAllUsersWhoResponded(Integer vacancyId) {
        List<User> users = userDao.getAllUsersWhoResponded(vacancyId).orElse(List.of());
        return users.stream().map(this::convertToUserDto).toList();
    }

    @Override
    public UserDto findApplicant(String email) {
        User user = userDao.findOneUserByEmail(email)
                .orElseThrow(UserNotFoundException ::new);

        return  convertToUserDto(userDao.findOneUserByEmail(email).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDto findEmployer(String email) {
        User user = userDao.findOneUserByEmail(email)
                .orElseThrow(UserNotFoundException ::new);

       return convertToUserDto(userDao.findOneUserByEmail(email).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users = userDao.getUserByName(name).orElseThrow(UserNotFoundException::new);
        return users.stream().map(this::convertToUserDto).toList();

    }

    @Override
    public List<UserDto> getUserByPhoneNumber(String phoneNumber) {
        List<User> users = userDao.getUserByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new);
        return users.stream().map(this::convertToUserDto).toList();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userDao.findOneUserByEmail(email)
                .orElseThrow(UserNotFoundException ::new);

        return convertToUserDto(user);
    }

    @Override
    public Boolean isUserAuthorized(String email) {
        return userDao.isUserAuthorized(email);
    }

    private UserDto convertToUserDto(User user){

        return
                UserDto.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .age(user.getAge())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .phoneNumber(user.getPhoneNumber())
                        .avatar(user.getAvatar())
                        .role(user.getRole())
                        .build();

    }

    private User convertToUser(UserDto userDto){
        return User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .avatar(userDto.getAvatar())
                .role(userDto.getRole())
                .build();
    }

    private User correctUserForDao(User user, User checkUser) {
        if (checkUser == null) {
            return user;
        }

        return User.builder()
                .name(Optional.ofNullable(checkUser.getName()).orElse(user.getName()))
                .surname(Optional.ofNullable(checkUser.getSurname()).orElse(user.getSurname()))
                .age(Optional.ofNullable(checkUser.getAge()).orElse(user.getAge()))
                .email(Optional.ofNullable(checkUser.getEmail()).orElse(user.getEmail()))
                .password(Optional.ofNullable(checkUser.getPassword()).orElse(user.getPassword()))
                .phoneNumber(Optional.ofNullable(checkUser.getPhoneNumber()).orElse(user.getPhoneNumber()))
                .avatar(Optional.ofNullable(checkUser.getAvatar()).orElse(user.getAvatar()))
                .role(user.getRole())
                .build();
    }

}
