package dev.dorigo.springcommerce.api.controller.response;

import dev.dorigo.springcommerce.api.domain.Category;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductResponse(Long id,
                              String name,
                              String description,
                              BigDecimal price,
                              String imageUrl,
                              Integer stockQuantity,
                              List<CategoryResponse> category) {
}
