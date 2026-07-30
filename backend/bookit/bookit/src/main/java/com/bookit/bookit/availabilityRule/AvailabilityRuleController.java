package com.bookit.bookit.availabilityRule;

import com.bookit.bookit.availabilityRule.dto.AvailabilityRuleDTO;
import com.bookit.bookit.availabilityRule.dto.CreateAvailabilityRuleDTO;
import com.bookit.bookit.availabilityRule.dto.PatchAvailabilityRuleDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availabilityRule")
@RequiredArgsConstructor
public class AvailabilityRuleController {
    private final AvailabilityRuleService availabilityRuleService;

    @GetMapping
    public ResponseEntity<List<AvailabilityRuleDTO>> getAvailabilityRules() {
        return ResponseEntity.ok(availabilityRuleService.getAvailabilityRules());
    }

    @GetMapping("/{staffmemberId}")
    public ResponseEntity<List<AvailabilityRuleDTO>> getAvailabilityRulesByStaffmemberId(@PathVariable Integer staffmemberId) {
        return ResponseEntity.ok(availabilityRuleService.getAvailabilityRulesByStaffmemberId(staffmemberId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchAvailabilityRule(@PathVariable Integer id, @Valid @RequestBody PatchAvailabilityRuleDTO patchAvailabilityRuleDTO) {
        availabilityRuleService.patchAvailabilityRule(id, patchAvailabilityRuleDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<Void> postAvailabilityRule(@Valid @RequestBody CreateAvailabilityRuleDTO createAvailabilityRuleDTO) {
        availabilityRuleService.postAvailabilityRule(createAvailabilityRuleDTO);
        return ResponseEntity.status(201).build();
    }
}
