package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "my_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    @Column//(nullable = false, length = 255)
    private String username;
    //@NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 100, message = "Фамилия должна быть от 2 до 100 символов длиной")
    @Column//(nullable = false, length = 255)
    private String surname;
    //@NotEmpty(message = "Поле не должно быть пустым")
    //    @Min(value = 1, message = "Возраст не может быть меньше 5")
    //@Size(min = 1, max = 100, message = "Возраст не может быть меньше 1")
    @Column(nullable = false)
    private Long age;
    //@NotEmpty(message = "Поле не должно быть пустым")
    //@Column(name = "email")
    @Size(min=2, message = "Не меньше 5 знаков")
    @Column//(nullable = false, length = 255)
    private String email;
    //@NotEmpty(message = "Поле не должно быть пустым")
    //@Column(name = "password")
    @Size(min=2, message = "Не меньше 5 знаков")
    @Column//(nullable = false, length = 255)
    private String password;
    //@Transient
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    public User() {

    }

//    public User(Long id, String name, String surname, Long age, String email, String password) {
//        this.id = id;
//        this.name = name;
//        this.surname = surname;
//        this.age = age;
//        this.email = email;
//        this.password = password;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return getRoles();
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + username + '\'' +
//                ", surname='" + surname + '\'' +
//                ", age=" + age +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}';


//    private final User user;
//    public User getUser() {
//        return this.user;
//    }
}
