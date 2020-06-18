package com.example.Forum.web;

import com.example.Forum.model.Category;
import com.example.Forum.model.helpers.CategoryModel;
import com.example.Forum.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    private IndexController indexController;

    public CategoryController(CategoryService categoryService, IndexController indexController) {
        this.categoryService = categoryService;
        this.indexController = indexController;
    }

    @RequestMapping("/create")
    public String createCategory (@ModelAttribute(value = "Cat") CategoryModel categoryModel, Model model){
        categoryService.createCategory(categoryModel);
        return indexController.getAllCategories(model);
    }


    @RequestMapping("/delete/{categoryId}")
    public String deleteThread (@PathVariable(value = "categoryId") Long id, Model model){
        categoryService.deleteCategoryById(id);
        return indexController.getAllCategories(model);
    }


    @RequestMapping("/edit/{categoryId}")
    public String editThread (@PathVariable(value = "categoryId") Long id, Model model){
        Category category = categoryService.getCategoryById(id);

        CategoryModel categoryModel =new CategoryModel();
        categoryModel.setCategoryId(category.getCategoryId());
        categoryModel.setCategoryName(category.getCategoryName());
        categoryModel.setCategoryDescription(category.getCategoryDescription());
        model.addAttribute("model", categoryModel);
        return"category/edit.html";

    }
}