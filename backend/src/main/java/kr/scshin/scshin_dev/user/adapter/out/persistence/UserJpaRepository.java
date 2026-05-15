package kr.scshin.scshin_dev.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByEmail(String email);

    @Query("select u from UserJpaEntity u left join fetch u.roles where u.email = :email")
    Optional<UserJpaEntity> findByEmailWithRoles(@Param("email") String email);
}
