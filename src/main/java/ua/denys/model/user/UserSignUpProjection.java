package ua.denys.model.user;

import lombok.Data;

@Data
public class UserSignUpProjection {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
