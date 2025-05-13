package dev.dorigo.springcommerce.api.repository;

import dev.dorigo.springcommerce.api.domain.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
