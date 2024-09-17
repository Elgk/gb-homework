package gb.spring.service;

import gb.spring.model.Product;
import gb.spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public String findById(Long id){
        return productRepository.findById(id);
    }

    public void save(Product product){
        productRepository.saveProduct(product);
    }

    public void deleteByID(Long id){
        productRepository.deleteByID(id);
    }
}
