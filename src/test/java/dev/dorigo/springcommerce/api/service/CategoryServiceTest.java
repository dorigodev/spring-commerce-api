package dev.dorigo.springcommerce.api.service;

import dev.dorigo.springcommerce.api.controller.request.CategoryRequest;
import dev.dorigo.springcommerce.api.domain.Category;
import dev.dorigo.springcommerce.api.mapper.CategoryMapper;
import dev.dorigo.springcommerce.api.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldSaveCategory() {
        CategoryRequest category = new CategoryRequest("Name Category Teste ");
        Category saveCategory = CategoryMapper.toCategory(category);
        saveCategory.setId(1L);

        when(categoryRepository.save(any(Category.class))).thenReturn(saveCategory);

        var result = categoryService.save(category);
        assertEquals(saveCategory.getId(), result.getId());
        assertEquals(saveCategory.getName(), result.getName());

        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void shouldReturnCategoryById(){
      Category category = new Category(1L, "Name Category");

      when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

      var result = categoryService.getyById(1L);

      assertEquals(category, result);
      verify(categoryRepository).findById(1L);
    }

    @Test
    void shouldReturnAllCategorys(){
        List<Category> categories = List.of(
                new Category(1L, "Name Category 1"),
                new Category(2L, "Name Category 2"),
                new Category(3L, "Name Category 3")
        );


        when(categoryRepository.findAll()).thenReturn(categories);

        var result = categoryService.getAll();
        assertEquals(categories, result);
        assertEquals(categories.size(), result.size());
        assertEquals("Name Category 2", result.get(1).getName() );
        verify(categoryRepository).findAll();
    }

    @Test
    void shouldUpdateCategory(){
        Category category = new Category(1L, "Name Category ");
        CategoryRequest categoryRequest = new CategoryRequest("New Name Category");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        var result = categoryService.update(1L, categoryRequest);

        assertEquals(category, result);
        verify(categoryRepository).findById(1L);
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void shouldDeleteCategory(){
        Category category = new Category(1L, "Name Category");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        categoryService.delete(1L);

        verify(categoryRepository).delete(category);
    }

}