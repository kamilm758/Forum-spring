package com.example.Forum.service;

import com.example.Forum.model.Category;
import com.example.Forum.model.helpers.CategoryModel;
import com.example.Forum.repository.CategoryRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    @Mock
    CategoryRepo categoryRepoMock;

    @InjectMocks
    CategoryService categoryImpl;

    @Test
    public void checkGetCategoryById(){
        Category exampleCategory = new Category();
        exampleCategory.setCategoryId(1L);
        exampleCategory.setCategoryName("Informatyka");
        when(categoryRepoMock.getByCategoryId(1L)).thenReturn(exampleCategory);

        Category expectedCategory = categoryImpl.getCategoryById(1L);

        Assert.assertEquals("Informatyka",expectedCategory.getCategoryName() );
    }

    @Test
    public void checkCreateCategory(){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategoryDescription("Some description");
        categoryModel.setCategoryName("Example name");
        categoryModel.setCategoryId(10L);

        Category category = categoryImpl.createCategory(categoryModel);

        Assert.assertEquals("Example name",category.getCategoryName());
        Assert.assertEquals("Some description", category.getCategoryDescription());
        Assert.assertEquals("10", category.getCategoryId().toString());
    }

    @Test
    public void checkGetAllCategories(){
        List<Category> exampleCategories = getSampleCategoryData();
        when(categoryRepoMock.findAll()).thenReturn(exampleCategories);

        Iterable<Category> result = categoryImpl.getAll();
        long numberOfCategories = result.spliterator().getExactSizeIfKnown();

        Assert.assertEquals("2",Long.toString(numberOfCategories));
    }

    public List<Category> getSampleCategoryData(){
        List<Category> result = new ArrayList<>();
        Category cat1 = new Category();
        Category cat2 = new Category();
        result.add(cat1);
        result.add(cat2);
        return result;
    }
}
