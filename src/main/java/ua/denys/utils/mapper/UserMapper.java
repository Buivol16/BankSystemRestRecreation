package ua.denys.utils.mapper;

import org.mapstruct.Mapper;
import ua.denys.model.user.User;
import ua.denys.model.user.UserSignUpProjection;

@Mapper
public interface UserMapper {
    User signUpProjectionToUser(UserSignUpProjection signUpProjection);
}
