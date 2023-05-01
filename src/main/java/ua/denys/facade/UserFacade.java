package ua.denys.facade;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.denys.enums.Role;
import ua.denys.exception.UsernameIsExistsException;
import ua.denys.model.user.User;
import ua.denys.model.user.UserSignUpProjection;
import ua.denys.repository.UserRepository;

import java.io.IOException;

import static ua.denys.enums.Role.USER;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final HttpServletResponse response;

    public User findUserByUsername(String username) {
        final var userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) return userOptional.get();
        else throw new UsernameNotFoundException("Username is not exists.");
    }

    public User registerUser(UserSignUpProjection projection) throws IOException {
        if (checkUsernameExisting(projection.getUsername()))
            throw new UsernameIsExistsException("This username is already exists.");

        final var buildedUser = User.builder()
                .firstName(projection.getFirstName())
                .lastName(projection.getLastName())
                .role(USER)
                .username(projection.getUsername())
                .password(projection.getPassword())
                .build();
        response.sendRedirect("/bank/home");
        return userRepository.save(buildedUser);
    }

    public boolean checkUsernameExisting(String username) {
        return userRepository.existsByUsername(username);
    }

}
