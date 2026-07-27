package com.bookit.bookit.user.dto;

import com.bookit.bookit.user.User;

public class UserMapper {
    public static UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSurname()
        );
    }

    public static User toEntity(UpdateUserDTO updateUserDTO, User user) {
        return user
                .setEmail(updateUserDTO.getEmail())
                .setName(updateUserDTO.getName())
                .setSurname(updateUserDTO.getSurname());
    }
}
