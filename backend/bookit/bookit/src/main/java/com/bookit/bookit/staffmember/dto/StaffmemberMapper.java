package com.bookit.bookit.staffmember.dto;

import com.bookit.bookit.staffmember.Staffmember;
import com.bookit.bookit.staffmember.StaffmemberService;

public class StaffmemberMapper {
    public static StaffmemberDTO toDto(Staffmember staffmember) {
        return new StaffmemberDTO(
                staffmember.getId(),
                staffmember.getBusiness(),
                staffmember.getNickname(),
                staffmember.isAvailable()
        );
    }

    public static Staffmember patchStaffmember(PatchStaffmemberDTO patchStaffmemberDTO, Staffmember staffmember) {
        if (patchStaffmemberDTO.getNickname() != null) {
            staffmember.setNickname(patchStaffmemberDTO.getNickname());
        }
        if (patchStaffmemberDTO.getAvailable() != null) {
            staffmember.setAvailable(patchStaffmemberDTO.getAvailable());
        }
        return staffmember;
    }
}
