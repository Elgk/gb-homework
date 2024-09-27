package gb.spring.repository;

import gb.spring.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public interface ProductDao {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void saveProduct(Product product);
    void deleteById(Long id);
}
