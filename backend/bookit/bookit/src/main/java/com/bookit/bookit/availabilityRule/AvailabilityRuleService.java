package com.bookit.bookit.availabilityRule;

import com.bookit.bookit.availabilityRule.dto.AvailabilityRuleDTO;
import com.bookit.bookit.availabilityRule.dto.AvailabilityRuleMapper;
import com.bookit.bookit.availabilityRule.dto.CreateAvailabilityRuleDTO;
import com.bookit.bookit.availabilityRule.dto.PatchAvailabilityRuleDTO;
import com.bookit.bookit.staffmember.Staffmember;
import com.bookit.bookit.staffmember.StaffmemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityRuleService {
    private final AvailabilityRuleRepository availabilityRuleRepository;
    private final StaffmemberRepository staffmemberRepository;

    public List<AvailabilityRuleDTO> getAvailabilityRules(){
        return availabilityRuleRepository.findAll().stream().map(AvailabilityRuleMapper::toDto).toList();
    }

    public List<AvailabilityRuleDTO> getAvailabilityRulesByStaffmemberId(Integer staffmemberId){
        staffmemberRepository.findById(staffmemberId)
                .orElseThrow(() -> new EntityNotFoundException("Staffmember not found with this id " + staffmemberId));
        return availabilityRuleRepository.findByStaffmemberId(staffmemberId).stream().map(AvailabilityRuleMapper::toDto).toList();
    }

    public void patchAvailabilityRule(Integer id, PatchAvailabilityRuleDTO patchAvailabilityRuleDTO) {
        AvailabilityRule oldAvailabilityRule = availabilityRuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AvailabilityRule not found with this id " + id));

        if (!patchAvailabilityRuleDTO.getStartTime().isBefore(patchAvailabilityRuleDTO.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        AvailabilityRule patchedAvailabilityRule = AvailabilityRuleMapper.patchAvailabilityRule(oldAvailabilityRule, patchAvailabilityRuleDTO);
        availabilityRuleRepository.save(patchedAvailabilityRule);
    }

    public void postAvailabilityRule(CreateAvailabilityRuleDTO createAvailabilityRuleDTO) {
        Staffmember staffmember = staffmemberRepository.findById(createAvailabilityRuleDTO.getStaffmemberId())
                .orElseThrow(() -> new EntityNotFoundException(("Staffmember not found with this id " + createAvailabilityRuleDTO.getStaffmemberId())));

        if (!createAvailabilityRuleDTO.getStartTime().isBefore(createAvailabilityRuleDTO.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        AvailabilityRule availabilityRule = new AvailabilityRule()
                .setDayOfWeek(createAvailabilityRuleDTO.getDayOfWeek())
                .setStartTime(createAvailabilityRuleDTO.getStartTime())
                .setEndTime(createAvailabilityRuleDTO.getEndTime())
                .setStaffmember(staffmember);

        availabilityRuleRepository.save(availabilityRule);
    }
}
