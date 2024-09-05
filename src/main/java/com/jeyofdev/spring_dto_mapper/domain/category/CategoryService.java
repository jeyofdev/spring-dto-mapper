package com.jeyofdev.spring_dto_mapper.domain.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " cannot be found"));
    }

    public Category updateById(Long categoryId, Category updateCategoryData) {
        Category currentCategory = findById(categoryId);
        currentCategory.setTitle(updateCategoryData.getTitle());

        return categoryRepository.save(currentCategory);
    }

    public String deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return "Category with id " + categoryId + " deleted";
    }
}
