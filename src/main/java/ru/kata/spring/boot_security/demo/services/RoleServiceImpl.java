package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositopies.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> saveAll(List<Role> role) {
        return roleRepository.saveAll(role);
    }


}
