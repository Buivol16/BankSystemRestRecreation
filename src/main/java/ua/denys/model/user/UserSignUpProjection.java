package ua.denys.model.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSignUpProjection {
    String firstName;
    String lastName;
    String username;
    String password;
}
