package com.bookit.bookit.category.dto;

import com.bookit.bookit.category.Category;

public class CategoryMapper {
    public static CategoryDTO toDto(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
