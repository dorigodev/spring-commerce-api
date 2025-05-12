package dev.dorigo.springcommerce.api.mapper;

import dev.dorigo.springcommerce.api.controller.request.ProductRequest;
import dev.dorigo.springcommerce.api.controller.response.CategoryResponse;
import dev.dorigo.springcommerce.api.controller.response.ProductResponse;
import dev.dorigo.springcommerce.api.domain.Category;
import dev.dorigo.springcommerce.api.domain.Product;

import java.util.List;

public class ProductMapper {
    public static Product toProduct(ProductRequest request){
        List<Category> categories = request.category()
                .stream()
                .map(categoryID -> Category.builder().id(categoryID).build())
                .toList();

        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .imageUrl(request.imageUrl())
                .stockQuantity(request.stockQuantity())
                .category(categories)
                .build();
    }

    public static ProductResponse toResponse(Product product){
        List<CategoryResponse> categories = product.getCategory()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .stockQuantity(product.getStockQuantity())
                .category(categories)
                .build();
    }
}
