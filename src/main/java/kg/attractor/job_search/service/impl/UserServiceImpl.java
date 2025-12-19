package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.UserDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;


    @Override
    public void create(UserDto userDto) {
        // TODO логика будет добавлена позже
    }

    @Override
    public ResponseEntity<List<UserDto>> showAllUsersWhoResponded(Integer vacancyId) {
        // TODO логика будет добавлена позже
        return null;
    }

    @Override
    public ResponseEntity<UserDto> findApplicant(String email) {
        // TODO логика будет добавлена позже
        return null;
    }

    @Override
    public ResponseEntity<UserDto> findEmployer(String email) {
        // TODO логика будет добавлена позже
        return null;
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users = userDao.getUserByName(name);

        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return convertToUserDto(users);

    }

    @Override
    public List<UserDto> getUserByPhoneNumber(String phoneNumber) {
        List<User> users = userDao.getUserByPhoneNumber(phoneNumber);

        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return convertToUserDto(users);
    }

    @Override
    public List<UserDto> getUserByEmail(String email) {
        List<User> users = userDao.getUserByEmail(email);

        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return convertToUserDto(users);
    }

    @Override
    public boolean isUserAuthorized(String email) {
        return userDao.isUserAuthorized(email);
    }

    private List<UserDto> convertToUserDto(List<User> users){
        List<UserDto> result = new ArrayList<>();

        users.forEach(user -> result.add(
                UserDto.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .age(user.getAge())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .avatar(user.getAvatar())
                        .role(user.getRole())
                        .build()
        ));

        return result;

    }
}
