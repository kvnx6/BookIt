package com.bookit.bookit.staffmember;

import com.bookit.bookit.staffmember.dto.CreateStaffmemberDTO;
import com.bookit.bookit.staffmember.dto.PatchStaffmemberDTO;
import com.bookit.bookit.staffmember.dto.StaffmemberDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stuffmember")
@RequiredArgsConstructor
public class StaffmemberController {
    private final StaffmemberService staffmemberService;

    @GetMapping
    public ResponseEntity<List<StaffmemberDTO>> getStaffmembers() {
        return ResponseEntity.ok(staffmemberService.getStuffmembers());
    }

    @GetMapping("/{businessId}")
    public ResponseEntity<List<StaffmemberDTO>> getStaffmembersByBusinessId(@PathVariable Integer businessId) {
        return ResponseEntity.ok(staffmemberService.getStaffmembersByBusinessId(businessId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchStaffmember(@PathVariable Integer id, @Valid @RequestBody PatchStaffmemberDTO patchStaffmemberDTO) {
        staffmemberService.patchStuffmember(id, patchStaffmemberDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> postStaffmember(@Valid @RequestBody CreateStaffmemberDTO createStaffmemberDTO) {
        staffmemberService.postStaffmember(createStaffmemberDTO);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteStaffmember(@PathVariable Integer id) {
        staffmemberService.deleteStaffmember(id);
        return ResponseEntity.ok().build();
    }
}
