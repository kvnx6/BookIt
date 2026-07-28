package com.bookit.bookit.staffmember;

import com.bookit.bookit.business.Business;
import com.bookit.bookit.business.BusinessRepository;
import com.bookit.bookit.staffmember.dto.CreateStaffmemberDTO;
import com.bookit.bookit.staffmember.dto.PatchStaffmemberDTO;
import com.bookit.bookit.staffmember.dto.StaffmemberDTO;
import com.bookit.bookit.staffmember.dto.StaffmemberMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffmemberService {
    private final StaffmemberRepository staffmemberRepository;
    private final BusinessRepository businessRepository;

    public List<StaffmemberDTO> getStuffmembers() {
        return staffmemberRepository.findAll().stream().map(StaffmemberMapper::toDto).toList();
    }

    public List<StaffmemberDTO> getStaffmembersByBusinessId(Integer businessId) {
        businessRepository.findById(businessId).orElseThrow(() -> new EntityNotFoundException("Business not found with this id: " + businessId));
        return staffmemberRepository.findStaffmemberByBusinessId(businessId).stream().map(StaffmemberMapper::toDto).toList();
    }

    public void patchStuffmember(Integer id, PatchStaffmemberDTO patchStaffmemberDTO) {
        Staffmember staffmember = staffmemberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff member not found with id: " + id));
        Staffmember patchedStaffmember = StaffmemberMapper.patchStaffmember(patchStaffmemberDTO, staffmember);
        staffmemberRepository.save(patchedStaffmember);
    }

    public void postStaffmember(CreateStaffmemberDTO createStaffmemberDTO) {
        Business business = businessRepository.findById(createStaffmemberDTO.getBusinessId())
                .orElseThrow(() -> new EntityNotFoundException("Business not found with this id: " + createStaffmemberDTO.getBusinessId()));

        Staffmember staffmember = new Staffmember()
                .setBusiness(business)
                .setNickname(createStaffmemberDTO.getNickname())
                .setAvailable(true);
        staffmemberRepository.save(staffmember);
    }

    public void deleteStaffmember(Integer id) {
        Staffmember staffmember = staffmemberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff member not found with id: " + id));
        staffmemberRepository.deleteById(id);
    }
}
