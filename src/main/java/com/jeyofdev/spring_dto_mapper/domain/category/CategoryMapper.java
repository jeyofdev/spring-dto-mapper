package com.jeyofdev.spring_dto_mapper.domain.category;

import com.jeyofdev.spring_dto_mapper.common.BaseDomainMapper;
import com.jeyofdev.spring_dto_mapper.domain.category.dto.CategoryDTO;
import com.jeyofdev.spring_dto_mapper.domain.category.dto.SaveCategoryDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryMapper implements BaseDomainMapper<Category, CategoryDTO, SaveCategoryDTO> {
    private final MovieMapper movieMapper;

    @Override
    public CategoryDTO mapFromEntity(Category category, boolean... args) {
        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                args.length == 1 && args[0] ?
                        category.getMovieList().stream()
                        .map(movie -> movieMapper.mapFromEntity(movie, false, false))
                        .collect(Collectors.toList()) : null
        );
    }

    @Override
    public Category mapToEntity(SaveCategoryDTO saveCategoryDTO) {
        Category category = new Category();
        category.setTitle(saveCategoryDTO.title());

        return category;
    }
}
