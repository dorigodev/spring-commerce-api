package dev.dorigo.springcommerce.api.domain;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String imageUrl;

    @Column(nullable = false, name = "stock_quantity")
    private Integer stockQuantity;

    @ManyToMany
    @JoinTable(name = "product_category",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> category;

}
