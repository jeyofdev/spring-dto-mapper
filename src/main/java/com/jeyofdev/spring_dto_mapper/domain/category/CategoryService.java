package com.jeyofdev.spring_dto_mapper.domain.category;

import com.jeyofdev.spring_dto_mapper.common.AbstractDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractDomainService<Category> {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository, "category");
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category updateById(Long categoryId, Category updateCategoryData) {
        Category currentCategory = findById(categoryId);
        currentCategory.setTitle(updateCategoryData.getTitle());

        return categoryRepository.save(currentCategory);
    }
}
