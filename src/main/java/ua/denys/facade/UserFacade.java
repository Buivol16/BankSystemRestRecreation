package ua.denys.facade;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import ua.denys.exception.UsernameIsExistsException;
import ua.denys.model.user.User;
import ua.denys.model.user.UserSignUpProjection;
import ua.denys.repository.UserRepository;
import ua.denys.security.configuration.PasswordEncryptorService;

import static ua.denys.enums.Role.USER;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;

    public User findUserByUsernameOrThrowException(String username) {
        final var userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) return userOptional.get();
        else throw new UsernameNotFoundException("Username is not exists.");
    }

    public User registerUser(UserSignUpProjection projection) {
        final var username = projection.getUsername();
        projection.setPassword(
                PasswordEncryptorService.generateHashPassword(
                        projection.getPassword()));

        if (checkUsernameExisting(username))
            throw new UsernameIsExistsException("This username is already exists.");

        final var buildedUser = new User();
        buildedUser.setFirstName(projection.getFirstName());
        buildedUser.setLastName(projection.getLastName());
        buildedUser.setRole(USER);
        buildedUser.setUsername(username);
        buildedUser.setPassword(projection.getPassword());

        final var authentication = new UsernamePasswordAuthenticationToken(username, projection.getPassword());
        authentication.setDetails(new WebAuthenticationDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userRepository.save(buildedUser);
    }

    public boolean checkUsernameExisting(String username) {
        return userRepository.existsByUsername(username);
    }

}
