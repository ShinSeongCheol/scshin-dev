package kr.scshin.scshin_dev.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(2)
@RequiredArgsConstructor
public class RoleDataInitializer implements CommandLineRunner {

    private final UserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        Optional<UserJpaEntity> userJpaEntity = userJpaRepository.findByEmail("admin");

        userJpaEntity.ifPresent(user -> {
            boolean hasAdminRole = roleJpaRepository.existsByNameAndUser("ADMIN", user);

            if (!hasAdminRole) {
                RoleJpaEntity roleJpaEntity = RoleJpaEntity.builder()
                        .name("ADMIN")
                        .user(userJpaEntity.get())
                        .build();

                roleJpaRepository.save(roleJpaEntity);
            }
        });
    }
}
