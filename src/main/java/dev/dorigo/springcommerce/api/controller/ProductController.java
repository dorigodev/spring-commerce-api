package dev.dorigo.springcommerce.api.controller;

import dev.dorigo.springcommerce.api.controller.request.ProductRequest;
import dev.dorigo.springcommerce.api.controller.response.ProductResponse;
import dev.dorigo.springcommerce.api.domain.Product;
import dev.dorigo.springcommerce.api.mapper.ProductMapper;
import dev.dorigo.springcommerce.api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        Product product = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toResponse(product));
    }

}
