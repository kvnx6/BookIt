package com.bookit.bookit.staffmember.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreateStaffmemberDTO {
    @NotBlank
    @Size(max = 150, message = "Your nickname must not be longer than 150 characters")
    private String nickname;

    @NotNull(message = "Please specify which business this staff member belongs to")
    private Integer businessId;
}
