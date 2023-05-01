package ua.denys.security.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.denys.facade.UserFacade;

@Component
@RequiredArgsConstructor
public class BankUserDetailsService implements UserDetailsService {

    private final UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userFacade.findUserByUsernameOrThrowException(username);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(
                        new SimpleGrantedAuthority(
                                user.getRole().name()))
                .build();
    }
}
