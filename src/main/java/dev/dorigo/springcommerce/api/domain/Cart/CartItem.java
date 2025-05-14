package dev.dorigo.springcommerce.api.domain.Cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.dorigo.springcommerce.api.domain.Product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Min(1)
    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    private Cart cart;
}
