package kr.scshin.scshin_dev.user.adapter.out.persistence;

import kr.scshin.scshin_dev.user.domain.model.Role;
import kr.scshin.scshin_dev.user.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toDomain(UserJpaEntity userJpaEntity) {
        return new User(
                userJpaEntity.getId(),
                userJpaEntity.getEmail(),
                userJpaEntity.getPassword(),
                userJpaEntity.getRoles().stream().map(role -> new Role(role.getId(), role.getName())).toList()
        );
    }

}
