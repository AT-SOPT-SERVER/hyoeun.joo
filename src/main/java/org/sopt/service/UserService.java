package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.User;
import org.sopt.dto.request.user.UserCreateRequest;
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
    public void createUser(UserCreateRequest userCreateRequest) {
        Validator.validateEmpty(userCreateRequest.name());
        Validator.validateMaxLength(userCreateRequest.name());

        User user = new User(userCreateRequest.name());
        userRepository.save(user);
    }
}
