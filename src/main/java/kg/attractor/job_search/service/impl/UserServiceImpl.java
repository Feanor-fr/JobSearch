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
import kg.attractor.job_search.utils.ConvertHelper;
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

        User user = ConvertHelper.convertToUser(userDto);
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

        if (!user.getRole().equals(userDto.getRole())) {
            throw new WrongRoleException("Тип аккаунта изменить нельзя!");
        }

        if (!userDto.getEmail().equalsIgnoreCase(email)) {
            List<User> usersForCheckEmail = userDao.getUserByEmail(userDto.getEmail()).orElse(List.of());
            if (!usersForCheckEmail.isEmpty()) {
                throw new UserAlreadyExistsException("Этот email уже занят!");
            }
        }

        User check = ConvertHelper.convertToUser(userDto);
        userDao.update(email, ConvertHelper.correctUserForDao(user, check));

    }

    @Override
    public List<UserDto> showAllUsersWhoResponded(Integer vacancyId) {
        List<User> users = userDao.getAllUsersWhoResponded(vacancyId).orElse(List.of());
        return users.stream().map(ConvertHelper::convertToUserDto).toList();
    }

    @Override
    public UserDto findApplicant(String email) {
        User user = userDao.findOneUserByEmail(email)
                .orElseThrow(UserNotFoundException ::new);

        if (!user.getRole().equals(RoleType.APPLICANT.getId())) {
            throw new WrongRoleException("Пользователь не является соискателем");
        }

        return  ConvertHelper.convertToUserDto(user);
    }

    @Override
    public UserDto findEmployer(String email) {
        User user = userDao.findOneUserByEmail(email)
                .orElseThrow(UserNotFoundException ::new);

        if (!user.getRole().equals(RoleType.EMPLOYER.getId())) {
            throw new WrongRoleException("Пользователь не является работодателем");
        }

       return ConvertHelper.convertToUserDto(user);
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users = userDao.getUserByName(name).orElseThrow(UserNotFoundException::new);
        return users.stream().map(ConvertHelper::convertToUserDto).toList();

    }

    @Override
    public List<UserDto> getUserByPhoneNumber(String phoneNumber) {
        List<User> users = userDao.getUserByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new);
        return users.stream().map(ConvertHelper::convertToUserDto).toList();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userDao.findOneUserByEmail(email)
                .orElseThrow(UserNotFoundException ::new);

        return ConvertHelper.convertToUserDto(user);
    }

    @Override
    public Boolean isUserAuthorized(String email) {
        return userDao.isUserAuthorized(email);
    }

}
