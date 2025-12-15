package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


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
}
