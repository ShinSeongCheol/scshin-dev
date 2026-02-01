package kr.scshin.scshin_dev.user.adapter.out.persistence;

import kr.scshin.scshin_dev.user.domain.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public static Role toDomain(RoleJpaEntity roleJpaEntity) {
        return new Role(roleJpaEntity.getId(), roleJpaEntity.getName());
    }

}
