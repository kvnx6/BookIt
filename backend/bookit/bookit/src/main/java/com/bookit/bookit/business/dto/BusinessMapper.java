package com.bookit.bookit.business.dto;


import com.bookit.bookit.business.Business;

public class BusinessMapper {
    public static BusinessDTO toDto(Business business) {
        return new BusinessDTO(business.getId(),
                business.getOwner(),
                business.getCategory(),
                business.getName(),
                business.getUrlName(),
                business.getDescription(),
                business.getAddress(),
                business.getCity());
    }

    public static Business toEntity(Business business, UpdateBusinessDTO updateBusinessDTO) {
        return business
                .setAddress(updateBusinessDTO.getAddress())
                .setCity(updateBusinessDTO.getCity())
                .setDescription(updateBusinessDTO.getDescription());
    }
}
