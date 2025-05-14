package dev.dorigo.springcommerce.api.mapper;

import dev.dorigo.springcommerce.api.controller.request.UserRequest;
import dev.dorigo.springcommerce.api.controller.response.UserResponse;
import dev.dorigo.springcommerce.api.domain.User.User;
import dev.dorigo.springcommerce.api.domain.User.UserRole;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static User toUser(UserRequest request){
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .role(UserRole.ROLE_USER)
                .active(true)
                .build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
