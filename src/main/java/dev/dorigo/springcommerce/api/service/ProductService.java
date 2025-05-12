package dev.dorigo.springcommerce.api.service;

import dev.dorigo.springcommerce.api.controller.request.ProductRequest;
import dev.dorigo.springcommerce.api.domain.Category;
import dev.dorigo.springcommerce.api.domain.Product;
import dev.dorigo.springcommerce.api.mapper.ProductMapper;
import dev.dorigo.springcommerce.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesList = new ArrayList<>();
        for (Category category : categories) {
           categoriesList.add(categoryService.getyById(category.getId()));
        }
       return categoriesList;
    }
}
