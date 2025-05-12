package dev.dorigo.springcommerce.api.service;

import dev.dorigo.springcommerce.api.controller.request.CategoryRequest;
import dev.dorigo.springcommerce.api.domain.Category;
import dev.dorigo.springcommerce.api.mapper.CategoryMapper;
import dev.dorigo.springcommerce.api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    // CRUD - CREATE - READ - UPDATE - DELETE

    private final CategoryRepository repository;

    // Create
    public Category save(CategoryRequest categoryRequest) {
        Category category = CategoryMapper.toCategory(categoryRequest);
        return repository.save(category);
    }

    //Read (one)
    public Category getyById(Long id) {
        return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Read (all)

    public List<Category> getAll() {
        return repository.findAll();
    }

    //Update

    public Category update(Long id, CategoryRequest categoryRequest) {
        Category category = getyById(id);
        category.setName(categoryRequest.name());
        return repository.save(category);
    }

    //Delete
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
