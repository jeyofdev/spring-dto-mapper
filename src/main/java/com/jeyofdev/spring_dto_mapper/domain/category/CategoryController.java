package com.jeyofdev.spring_dto_mapper.domain.category;

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
    public ResponseEntity<Category> addNewCategory(@RequestBody Category newCategory) {
        Category createdCategory = categoryService.save(newCategory);

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.findById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.FOUND);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category newCategoryData) {
        Category createdCategory = categoryService.updateById(categoryId, newCategoryData);
        return new ResponseEntity<>(createdCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        String confirm = categoryService.deleteById(categoryId);
        return new ResponseEntity<>(confirm, HttpStatus.OK);
    }
}
