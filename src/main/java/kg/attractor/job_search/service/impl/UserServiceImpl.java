package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.UserDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public UserDto getUserByName(String name, String surname) {
        User user = userDao.getUserByName(name, surname)
                .orElseThrow(UserNotFoundException::new);
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setAge(user.getAge());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAvatar(user.getAvatar());
        userDto.setRole(user.getRole());

        return userDto;
    }
}
