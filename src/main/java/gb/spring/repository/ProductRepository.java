package gb.spring.repository;

import gb.spring.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private Map<Integer, Product> productList;
    private AtomicInteger counter = new AtomicInteger();

    public ProductRepository(){
        this.productList = new ConcurrentHashMap<>();
    }

    public List<Product> findAll(){
        return productList.values().stream().collect(Collectors.toUnmodifiableList());
    }

    public Optional<Product> findById(Integer id){
        return productList.values().stream()
                .filter((k) -> Objects.equals(k.getId(), id))
                .findFirst();
    }

    public void saveProduct(Product product){
        if (product.getId() == null){
            product.setId(counter.incrementAndGet());
        }
        productList.put(product.getId(), product);
    }

    public void deleteByID(Integer id){
        productList.remove(id);
    }
}
