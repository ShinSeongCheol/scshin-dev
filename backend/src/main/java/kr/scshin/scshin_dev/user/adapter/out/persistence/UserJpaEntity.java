package kr.scshin.scshin_dev.user.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class UserJpaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @OneToMany(mappedBy = "user")
    List<RoleJpaEntity> roles = new ArrayList<>();

    @Builder
    public UserJpaEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
