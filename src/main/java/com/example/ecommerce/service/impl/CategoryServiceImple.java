package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImple implements CategoryService {

    List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(cat->cat.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not ound"));

        categories.remove(category);
        return "category with caegry id "+categoryId+" deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Optional<Category> categoryOptional = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();
        if(categoryOptional.isPresent()){
            Category existingCategory = categoryOptional.get();
            category.setCategoryName(category.getCategoryName());
            return category;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId);
        }
    }
}
