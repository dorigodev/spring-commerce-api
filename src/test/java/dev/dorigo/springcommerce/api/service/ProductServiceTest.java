package dev.dorigo.springcommerce.api.service;

import dev.dorigo.springcommerce.api.controller.request.ProductRequest;
import dev.dorigo.springcommerce.api.domain.Category;
import dev.dorigo.springcommerce.api.domain.Product;
import dev.dorigo.springcommerce.api.mapper.ProductMapper;
import dev.dorigo.springcommerce.api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @Test
    void shouldSaveProduct() {
        Category category = new Category(1L, "name category");
        List<Long> categories = List.of(1L);

        ProductRequest product = new ProductRequest("Nome produto",
                "Descricao Produto",
                BigDecimal.TEN,
                "imagem.com",
                1050,
                    categories
                );
        Product saveProduct = ProductMapper.toProduct(product);
        saveProduct.setId(1L);
        saveProduct.setCategory(List.of(category));

        when(categoryService.getyById(1L)).thenReturn(category);
        when(productRepository.save(any())).thenReturn(saveProduct);

        var result = productService.save(product);
        
        assertEquals(saveProduct.getId(), result.getId());
        assertEquals(saveProduct.getName(), result.getName());
        assertEquals(saveProduct.getDescription(), result.getDescription());
        assertEquals(saveProduct.getPrice(), result.getPrice());
        assertEquals(saveProduct.getCategory(), result.getCategory());

        verify(productRepository).save(any());
    }


}