package com.bookit.bookit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class UserDTO {
    private Integer id;
    private String email;
    private String name;
    private String surname;
}
