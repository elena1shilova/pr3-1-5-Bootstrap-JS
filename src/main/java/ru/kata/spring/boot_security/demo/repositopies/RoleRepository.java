package ru.kata.spring.boot_security.demo.repositopies;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
