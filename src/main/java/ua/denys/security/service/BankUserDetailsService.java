package ua.denys.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import ua.denys.facade.UserFacade;
import ua.denys.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class BankUserDetailsService implements UserDetailsService, UserDetailsManager {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

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

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
