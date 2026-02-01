package kr.scshin.scshin_dev.user.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Getter
@NoArgsConstructor
public class RoleJpaEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @Builder
    public RoleJpaEntity(String name, UserJpaEntity user) {
        this.name = name;
        this.user = user;
    }
}
