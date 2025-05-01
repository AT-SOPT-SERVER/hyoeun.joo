package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.User;
import org.sopt.dto.request.UserCreate;
import org.sopt.repository.UserRepository;
import org.sopt.util.Validator;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(UserCreate userCreate) {
        Validator.validateEmpty(userCreate.name());
        Validator.validateMaxLength(userCreate.name());

        User user = new User(userCreate.name());
        userRepository.save(user);
    }
}
