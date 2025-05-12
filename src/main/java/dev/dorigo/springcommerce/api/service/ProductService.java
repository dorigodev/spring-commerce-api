package dev.dorigo.springcommerce.api.service;

import dev.dorigo.springcommerce.api.controller.request.ProductRequest;
import dev.dorigo.springcommerce.api.domain.Category;
import dev.dorigo.springcommerce.api.domain.Product;
import dev.dorigo.springcommerce.api.mapper.ProductMapper;
import dev.dorigo.springcommerce.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;

    public Product save(ProductRequest request) {
        Product product = ProductMapper.toProduct(request);
        product.setCategory(this.findCategories(product.getCategory()));
        return repository.save(product);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Product update(Long id, ProductRequest request) {
        var product = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var update = ProductMapper.toProduct(request);
        product.setName(update.getName());
        product.setDescription(update.getDescription());
        product.setPrice(update.getPrice());
        product.setImageUrl(update.getImageUrl());
        product.setStockQuantity(update.getStockQuantity());
        product.getCategory().clear();
        product.setCategory(this.findCategories(update.getCategory()));
        return repository.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        repository.delete(product);
    }

    public Product partialUpdate(Long id, ProductRequest productRequest) {
        var product = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (productRequest.name() != null) {
            product.setName(productRequest.name());
        }
        if (productRequest.description() != null) {
            product.setDescription(productRequest.description());
        }
        if (productRequest.price() != null) {
            product.setPrice(productRequest.price());
        }
        if (productRequest.imageUrl() != null) {
            product.setImageUrl(productRequest.imageUrl());
        }
        if (productRequest.stockQuantity() != null) {
            product.setStockQuantity(productRequest.stockQuantity());
        }
        if (productRequest.category() != null) {
            var update = ProductMapper.toProduct(productRequest);
            product.getCategory().clear();
            product.setCategory(this.findCategories(update.getCategory()));
        }
        return repository.save(product);

    }

    public List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesList = new ArrayList<>();
        for (Category category : categories) {
           categoriesList.add(categoryService.getyById(category.getId()));
        }
       return categoriesList;
    }
}
