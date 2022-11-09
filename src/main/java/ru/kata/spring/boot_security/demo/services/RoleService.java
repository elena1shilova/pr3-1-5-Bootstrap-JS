package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositopies.RoleRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService implements RoleServiceI{


    private final
    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

//    @Transactional
//    @Override
//    public Set<Role> saveRole(Long id) {
//         //Set<Role> role= new HashSet<>(Collections.singleton(roleRepository.getById(id)));
//         return role;
//    }

    @Override
    public List<Role> saveAll(List<Role> role) {
        return  roleRepository.saveAll(role);
    }
}
