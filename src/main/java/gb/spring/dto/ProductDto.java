package gb.spring.dto;

import gb.spring.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;

    public static ProductDto valueOf(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    public Product mapToProduct() {
        Product product = new Product();
        product.setId(id);
        product.setPrice(price);
        product.setName(name);
        return product;
    }

    public Product updProduct(Product p){
        p.setPrice(price);
        p.setName(name);
        return p;
    }
}
