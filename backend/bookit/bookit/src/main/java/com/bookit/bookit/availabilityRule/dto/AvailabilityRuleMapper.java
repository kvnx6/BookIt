package com.bookit.bookit.availabilityRule.dto;

import com.bookit.bookit.availabilityRule.AvailabilityRule;

public class AvailabilityRuleMapper {
    public static AvailabilityRuleDTO toDto(AvailabilityRule availabilityRule) {
        return new AvailabilityRuleDTO(availabilityRule.getId(),
                availabilityRule.getStaffmember(),
                availabilityRule.getDayOfWeek(),
                availabilityRule.getStartTime(),
                availabilityRule.getEndTime());
    }

    public static AvailabilityRule patchAvailabilityRule(AvailabilityRule oldAvailabilityRule, PatchAvailabilityRuleDTO patchAvailabilityRuleDTO) {
        if (patchAvailabilityRuleDTO.getStartTime() != null) {
            oldAvailabilityRule.setStartTime(patchAvailabilityRuleDTO.getStartTime());
        }
        if (patchAvailabilityRuleDTO.getEndTime() != null) {
            oldAvailabilityRule.setEndTime(patchAvailabilityRuleDTO.getEndTime());
        }
        return oldAvailabilityRule;
    }
}
