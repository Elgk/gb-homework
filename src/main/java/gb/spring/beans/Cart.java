package gb.spring.beans;

import gb.spring.dto.OrderItemDto;
import gb.spring.exceptions.ExceptionControllerHandler;
import gb.spring.exceptions.ResourceNotFoundException;
import gb.spring.model.OrderItem;
import gb.spring.model.Product;
import gb.spring.repository.ProductRepository;
import gb.spring.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@RequiredArgsConstructor
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int totalPrice;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();
    }

    public void addToCart(Long id){
        for (OrderItem item : items) {
            if (item.getProduct().getId().equals(id)) {
                item.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product newProduct = productService.findProductById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format("Unable to add product with %d code, it doesn't exit.", id)));
        OrderItem item = new OrderItem(newProduct);
        items.add(item);
        recalculate();

    }
    private void recalculate(){
        for (OrderItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void clear() {
        items.clear();
        recalculate();
    }
}
