package com.bookit.bookit.business;

import com.bookit.bookit.business.dto.BusinessDTO;
import com.bookit.bookit.business.dto.CreateBusinessDTO;
import com.bookit.bookit.business.dto.UpdateBusinessDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    @GetMapping
    public ResponseEntity<List<BusinessDTO>> getBusinesses() {
        return ResponseEntity.ok(businessService.getBusinesses());
    }

    @GetMapping("/{urlName}")
    public ResponseEntity<BusinessDTO> getBusiness(@PathVariable String urlName) {
        return ResponseEntity.ok(businessService.getBusinessByUrlName(urlName));
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<List<BusinessDTO>> getBusinessesByOwnerId(@PathVariable Integer ownerId) {
        return ResponseEntity.ok(businessService.getBusinessesByOwnerId(ownerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putBusiness(@PathVariable Integer id, @Valid @RequestBody UpdateBusinessDTO updateBusinessDTO) {
        businessService.putBusinessById(id, updateBusinessDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> postBusiness(@Valid @RequestBody CreateBusinessDTO createBusinessDTO) {
        businessService.postBusiness(createBusinessDTO);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Integer id) {
        businessService.deleteBusiness(id);
        return ResponseEntity.ok().build();
    }
}
