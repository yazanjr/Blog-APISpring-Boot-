package com.devtiro.blog.controller;

import com.devtiro.blog.domain.dtod.CategoryDto;
import com.devtiro.blog.domain.dtod.CreateCategoryRequest;
import com.devtiro.blog.domain.enities.Category;
import com.devtiro.blog.mappers.CategoryMapper;
import com.devtiro.blog.repositroies.CategoryRepository;
import com.devtiro.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCatrgories(){
        List<CategoryDto> categories = categoryService.listCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> newCategory (@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        CategoryDto SavedCategory = categoryService.saveCategory(createCategoryRequest);
        return new ResponseEntity<>(
                SavedCategory, HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content is standard for deletes
    }


}