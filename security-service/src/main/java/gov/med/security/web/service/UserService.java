package gov.med.security.web.service;

import gov.med.security.web.core.Users;
import gov.med.security.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Optional<Users> findByUsername(String email) {
        return userRepository.findByEmail(email);
    }
}
