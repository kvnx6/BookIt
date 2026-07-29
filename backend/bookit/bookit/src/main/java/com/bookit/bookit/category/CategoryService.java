package com.bookit.bookit.category;

import com.bookit.bookit.category.dto.CategoryDTO;
import com.bookit.bookit.category.dto.CategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toDto).toList();
    }

    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findCategoryByName(name)
                .orElseThrow(() -> new EntityNotFoundException("no category found with this name " + name));
        return CategoryMapper.toDto(category);
    }
}
