package dev.dorigo.springcommerce.api.repository;

import dev.dorigo.springcommerce.api.domain.Product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
