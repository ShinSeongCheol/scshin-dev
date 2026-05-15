package kr.scshin.scshin_dev.user.application.out;

import kr.scshin.scshin_dev.user.domain.model.User;

import java.util.Optional;

public interface LoadUserPort {
    Optional<User> loadUserByEmail(String email);
}
