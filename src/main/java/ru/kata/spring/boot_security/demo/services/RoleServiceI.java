package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;


public interface RoleServiceI {
    List<Role> allRoles();

    List<Role> saveAll(List<Role> role);

}
