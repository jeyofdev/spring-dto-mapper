package com.jeyofdev.spring_dto_mapper.domain.category;

import com.jeyofdev.spring_dto_mapper.domain.category.dto.CategoryDTO;
import com.jeyofdev.spring_dto_mapper.domain.category.dto.SaveCategoryDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /*@PostConstruct
    public void init() {
        Category category1 = new Category(1L, "Adventure");
        Category category2 = new Category(2L, "Action");
        Category category3 = new Category(3L, "Thriller");
        Category category4 = new Category(4L, "Crime");

        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);
        categoryService.save(category4);
    }*/

    @PostMapping
    public ResponseEntity<CategoryDTO> addNewCategory(@RequestBody SaveCategoryDTO saveCategoryDTO) {
        Category category = CategoryMapper.mapToEntity(saveCategoryDTO);
        Category createdCategory = categoryService.save(category);
        CategoryDTO categoryDTO = CategoryMapper.mapFromEntity(createdCategory, true);

        return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryDTO> categoriesDTO = categoryList.stream().map(category -> CategoryMapper.mapFromEntity(category, false)).toList();

        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.findById(categoryId);
        CategoryDTO categoryDTO = CategoryMapper.mapFromEntity(category, true);

        return new ResponseEntity<>(categoryDTO, HttpStatus.FOUND);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody SaveCategoryDTO saveCategoryDTO) {
        Category category = CategoryMapper.mapToEntity(saveCategoryDTO);
        Category updatedCategory = categoryService.updateById(categoryId, category);
        CategoryDTO categoryDTO = CategoryMapper.mapFromEntity(updatedCategory, true);

        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        String confirm = categoryService.deleteById(categoryId);
        return new ResponseEntity<>(confirm, HttpStatus.OK);
    }
}
