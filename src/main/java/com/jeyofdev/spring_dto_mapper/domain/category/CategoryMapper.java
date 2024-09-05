package com.jeyofdev.spring_dto_mapper.domain.category;

import com.jeyofdev.spring_dto_mapper.domain.category.dto.CategoryDTO;
import com.jeyofdev.spring_dto_mapper.domain.category.dto.SaveCategoryDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.MovieMapper;

import java.util.stream.Collectors;

public class CategoryMapper {
    public static CategoryDTO mapFromEntity(Category category, boolean includeRelation) {
        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                includeRelation ?
                        category.getMovieList().stream()
                        .map(movie -> MovieMapper.mapFromEntity(movie, false, false))
                        .collect(Collectors.toList()) : null
        );
    }

    public static Category mapToEntity(SaveCategoryDTO saveCategoryDTO) {
        Category category = new Category();
        category.setTitle(saveCategoryDTO.title());

        return category;
    }
}
