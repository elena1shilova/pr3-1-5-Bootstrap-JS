package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;


public interface RoleService {

    Role getRoleByName(String name);

    void saveRole(Role role);

    Set<Role> getAllRoles();

    Role findById(Long id);
}
