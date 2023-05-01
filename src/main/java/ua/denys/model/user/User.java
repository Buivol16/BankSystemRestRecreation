package ua.denys.model.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ua.denys.enums.Role;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = PRIVATE)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;
    String firstName;
    String lastName;
    Role role;
}
