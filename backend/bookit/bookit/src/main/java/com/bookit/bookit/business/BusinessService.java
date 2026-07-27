package com.bookit.bookit.business;

import com.bookit.bookit.business.dto.BusinessDTO;
import com.bookit.bookit.business.dto.BusinessMapper;
import com.bookit.bookit.business.dto.CreateBusinessDTO;
import com.bookit.bookit.business.dto.UpdateBusinessDTO;
import com.bookit.bookit.category.Category;
import com.bookit.bookit.category.CategoryRepository;
import com.bookit.bookit.user.User;
import com.bookit.bookit.user.UserRepository;
import com.bookit.bookit.user.dto.UpdateUserDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<BusinessDTO> getBusinesses() {
        return businessRepository.findAll().stream().map(BusinessMapper::toDto).toList();
    }

    public BusinessDTO getBusinessByUrlName(String urlName) {
        Business business = businessRepository.findBusinessesByUrlName(urlName)
                .orElseThrow(() -> new EntityNotFoundException("Business not found with this UrlName" + urlName));
        return BusinessMapper.toDto(business);
    }

    public List<BusinessDTO> getBusinessesByOwnerId(Integer ownerId) {
        return businessRepository.findByOwnerId(ownerId).stream().map(BusinessMapper::toDto).toList();
    }

    @Transactional
    public void putBusinessById(Integer id, UpdateBusinessDTO updateBusinessDTO) {
        Business Oldbusiness = businessRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Business not found with this Id" + id));
        Business newBusiness = BusinessMapper.toEntity(Oldbusiness, updateBusinessDTO);
        businessRepository.save(newBusiness);
    }

    @Transactional
    public void postBusiness(CreateBusinessDTO createBusinessDTO) {
        if (businessRepository.existsBusinessByUrlName(createBusinessDTO.getUrlName())) {
            throw new EntityExistsException("Business already found with this UrlName");
        }

        User owner = userRepository.findById(createBusinessDTO.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + createBusinessDTO.getOwnerId()));

        Category category = categoryRepository.findById(createBusinessDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + createBusinessDTO.getCategoryId()));

        Business business = new Business()
                .setName(createBusinessDTO.getName())
                .setUrlName(createBusinessDTO.getUrlName())
                .setDescription(createBusinessDTO.getDescription())
                .setAddress(createBusinessDTO.getAddress())
                .setCity(createBusinessDTO.getCity())
                .setCategory(category)
                .setOwner(owner)
                .setCreatedAt(new Date());


        businessRepository.save(business);
    }
}
