package com.bookit.bookit.staffmember.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PatchStaffmemberDTO {
    @Size(max = 150, message = "your nickname must not be longer than 150")
    private String nickname;

    private Boolean available;
}
