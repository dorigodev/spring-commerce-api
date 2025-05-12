package dev.dorigo.springcommerce.api.repository;

import dev.dorigo.springcommerce.api.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
