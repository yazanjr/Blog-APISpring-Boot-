package com.devtiro.blog.services.impl;

import com.devtiro.blog.domain.dtod.CategoryDto;
import com.devtiro.blog.domain.dtod.CreateCategoryRequest;
import com.devtiro.blog.domain.enities.Category;
import com.devtiro.blog.mappers.CategoryMapper;
import com.devtiro.blog.mappers.CategoryMapperImpl;
import com.devtiro.blog.repositroies.CategoryRepository;
import com.devtiro.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> listCategories() {
        return categoryRepository.findAllWithPostCount().stream().map(categoryMapper::toDto).toList();
    }

    @Transactional
    public CategoryDto saveCategory(CreateCategoryRequest createCategoryRequest) {
        if(categoryRepository.existsByNameIgnoreCase(createCategoryRequest.getName())){
            throw new IllegalArgumentException("Categroy already exists with name:" + createCategoryRequest.getName());
        }
        else {
            Category category = categoryMapper.toEntity(createCategoryRequest);
        return categoryMapper.toDto(categoryRepository.save(category));
        }
    }

    @Override
    @Transactional
    public void deleteCategory(UUID id) {
        if(!categoryRepository.existsById(id)){
            throw new IllegalArgumentException("Categroy dose not exists with name:");
        } else if(categoryRepository.getReferenceById(id).getPosts().isEmpty())
                    categoryRepository.deleteById(id);
        else throw new IllegalArgumentException("Categroy has posts associated with it");
    }
}
