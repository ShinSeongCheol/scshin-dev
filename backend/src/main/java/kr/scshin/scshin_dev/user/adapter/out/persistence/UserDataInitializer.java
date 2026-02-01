package kr.scshin.scshin_dev.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(1)
@RequiredArgsConstructor
public class UserDataInitializer implements CommandLineRunner {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userJpaRepository.findByEmail("admin").isEmpty()) {
            UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                    .email("admin")
                    .password(passwordEncoder.encode("admin"))
                    .build();
            userJpaRepository.save(userJpaEntity);
        }
    }
}
