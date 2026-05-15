package kr.scshin.scshin_dev.user.domain.model;

import java.util.List;

public class User {
    private final Long id;
    private final String email;
    private final String password;
    private final List<Role> roles;

    public User(Long id, String email, String password, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
