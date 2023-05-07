package ua.denys.facade;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.denys.exception.UsernameIsExistsException;
import ua.denys.model.user.User;
import ua.denys.model.user.UserSignUpProjection;
import ua.denys.repository.UserRepository;
import ua.denys.security.service.BankAuthenticationService;
import ua.denys.utils.mapper.UserMapper;

import static ua.denys.enums.Role.USER;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User findUserByUsernameOrThrowException(String username) {
        final var userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) return userOptional.get();
        else throw new UsernameNotFoundException("Username is not exists.");
    }

    public User registerUser(UserSignUpProjection projection) {
        final var username = projection.getUsername();
        projection.setPassword(
                passwordEncoder.encode(
                        projection.getPassword()));

        if (checkUsernameExisting(username))
            throw new UsernameIsExistsException("This username is already exists.");

        final var mapper = Mappers.getMapper(UserMapper.class);
        final var user = mapper.signUpProjectionToUser(projection);
        user.setRole(USER.name());
        return userRepository.save(user);
    }

    public boolean checkUsernameExisting(String username) {
        return userRepository.existsByUsername(username);
    }

    public String getFirstName() {
        final var username = BankAuthenticationService.getUsername();
        return findUserByUsernameOrThrowException(username).getFirstName();
    }

}
