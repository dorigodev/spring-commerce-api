package dev.dorigo.springcommerce.api.mapper;

import dev.dorigo.springcommerce.api.controller.request.CategoryRequest;
import dev.dorigo.springcommerce.api.controller.response.CategoryResponse;
import dev.dorigo.springcommerce.api.domain.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest request){
        return Category.builder()
                .name(request.name())
                .build();
    }

    public static CategoryResponse toResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
