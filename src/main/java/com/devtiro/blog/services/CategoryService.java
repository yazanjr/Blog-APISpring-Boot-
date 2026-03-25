package com.devtiro.blog.services;

import com.devtiro.blog.domain.dtod.CategoryDto;
import com.devtiro.blog.domain.dtod.CreateCategoryRequest;
import com.devtiro.blog.domain.enities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryDto> listCategories();
    CategoryDto saveCategory(CreateCategoryRequest createCategoryRequest);
    void deleteCategory(UUID id);
}
