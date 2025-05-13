package dev.dorigo.springcommerce.api.controller;

import dev.dorigo.springcommerce.api.controller.request.CategoryRequest;
import dev.dorigo.springcommerce.api.controller.response.CategoryResponse;
import dev.dorigo.springcommerce.api.domain.Product.Category;
import dev.dorigo.springcommerce.api.mapper.CategoryMapper;
import dev.dorigo.springcommerce.api.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CategoryRequest request) {
        var category = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toResponse(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(CategoryMapper.toResponse(service.getyById(id)));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(service.getAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody @Valid CategoryRequest request) {
        var category = service.update(id, request);
        return ResponseEntity.ok(CategoryMapper.toResponse(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Category> category = Optional.ofNullable(service.getyById(id));
        if (category.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
