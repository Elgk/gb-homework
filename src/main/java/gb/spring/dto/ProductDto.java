package gb.spring.dto;

import gb.spring.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private String title;
    private int price;
    private Long id;

    public ProductDto(Product product) {
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.id = product.getId();
    }
}
