package gov.med.security.config;

import gov.med.security.web.core.Users;
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
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        // return UserDetails not Users
        return new User (
                users.getUsername(),
                users.getPassword(),
                users.getAuthorities()
        );
    }
}
