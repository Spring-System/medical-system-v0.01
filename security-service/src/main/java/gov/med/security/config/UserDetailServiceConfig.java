package gov.med.security.config;

import gov.med.security.web.core.Users;
import gov.med.security.web.repository.UserRepository;
import gov.med.security.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailServiceConfig implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        // return UserDetails not Users
        return new User (
                users.getUsername(),
                users.getPassword(),
                users.getAuthorities()
        );
    }
}
