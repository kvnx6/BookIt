package com.bookit.bookit.availabilityRule.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PatchAvailabilityRuleDTO {
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
}
