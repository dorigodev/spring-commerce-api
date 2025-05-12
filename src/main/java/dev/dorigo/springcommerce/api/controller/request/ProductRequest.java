package dev.dorigo.springcommerce.api.controller.request;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductRequest( String name,
                              String description,
                              BigDecimal price,
                              String imageUrl,
                              Integer stockQuantity,
                              List<Long> category) {
}
