package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleServiceI {
    public List<Role> allRoles();

    //public Set<Role> saveRole(Long id);
    public List<Role> saveAll(List<Role> role);

}
