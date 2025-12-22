package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.UserDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.exceptions.UserNotFoundException;
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
        User user = convertToUser(userDto);
        List<User> users = userDao.getAllUsers();

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                throw new IllegalStateException("User with this email already created");
            }
        }

        userDao.save(user);
    }

    @Override
    public void update(String email, UserDto userDto){
        User user = userDao.getUserByEmail(email).getFirst();

        if(user == null){
            throw new UserNotFoundException();
        }

        User check = convertToUser(userDto);
        userDao.update(email, correctUserForDao(user, check));

    }

    @Override
    public List<UserDto> showAllUsersWhoResponded(Integer vacancyId) {
        List<User> users = userDao.getAllUsersWhoResponded(vacancyId);

        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream().map(this::convertToUserDto).toList();

    }

    @Override
    public List<UserDto> findApplicant(String email) {
        List<User> users = userDao.getUserByEmail(email);

        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream().map(this::convertToUserDto).toList();
    }

    @Override
    public List<UserDto> findEmployer(String email) {
        List<User> users = userDao.getUserByEmail(email);

        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream().map(this::convertToUserDto).toList();
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users = userDao.getUserByName(name);

        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream().map(this::convertToUserDto).toList();

    }

    @Override
    public List<UserDto> getUserByPhoneNumber(String phoneNumber) {
        List<User> users = userDao.getUserByPhoneNumber(phoneNumber);

        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream().map(this::convertToUserDto).toList();
    }

    @Override
    public List<UserDto> getUserByEmail(String email) {
        List<User> users = userDao.getUserByEmail(email);

        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream().map(this::convertToUserDto).toList();
    }

    @Override
    public boolean isUserAuthorized(String email) {
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
                .password(Optional.ofNullable(checkUser.getPassword()).orElse(user.getPassword()))
                .phoneNumber(Optional.ofNullable(checkUser.getPhoneNumber()).orElse(user.getPhoneNumber()))
                .avatar(Optional.ofNullable(checkUser.getAvatar()).orElse(user.getAvatar()))
                .role(Optional.ofNullable(checkUser.getRole()).orElse(user.getRole()))
                .build();
    }

}
