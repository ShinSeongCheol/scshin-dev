package kr.scshin.scshin_dev.user.adapter.out.persistence;

import kr.scshin.scshin_dev.user.application.out.LoadUserPort;
import kr.scshin.scshin_dev.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> loadUserByEmail(String email) {
        return userJpaRepository.findByEmailWithRoles(email).map(UserMapper::toDomain);
    }
}
