package kr.scshin.scshin_dev.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleJpaEntity, Long> {
    boolean existsByNameAndUser(String email, UserJpaEntity userJpaEntity);
}
