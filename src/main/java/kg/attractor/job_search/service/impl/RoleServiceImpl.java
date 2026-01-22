package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.exceptions.RoleNotFoundException;
import kg.attractor.job_search.model.Role;
import kg.attractor.job_search.repository.RoleRepository;
import kg.attractor.job_search.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findRoleById(Integer id) {

        return roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new);
    }
}
