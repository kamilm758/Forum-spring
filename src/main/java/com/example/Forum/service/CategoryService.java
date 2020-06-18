package com.example.Forum.service;

import com.example.Forum.model.Category;
import com.example.Forum.model.helpers.CategoryModel;
import com.example.Forum.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public Category createCategory (CategoryModel categoryModel){
        Category category = new Category();
        if(categoryModel.getCategoryId()!=null){
            category.setCategoryId(categoryModel.getCategoryId());
        }
        category.setCategoryName(categoryModel.getCategoryName());
        category.setCategoryDescription(categoryModel.getCategoryDescription());

        categoryRepo.save(category);
        return category;
    }

    public Iterable<Category> getAll(){
        return categoryRepo.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.getByCategoryId(id);
    }

    public void deleteCategoryById(Long id) {
        categoryRepo.deleteById(id);
    }
}