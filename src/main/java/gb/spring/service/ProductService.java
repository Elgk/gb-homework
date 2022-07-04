package gb.spring.service;

import gb.spring.model.Product;
import gb.spring.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll(Sort.by("price"));
    }

    public List<Product> findAllLikeTitle(String name){
        Product productExample = new Product();
        productExample.setTitle(name);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return productRepository.findAll(Example.of(productExample, matcher), Sort.by("title"));
    }

    public List<Product> findAllByPriceBetween(Integer min, Integer max){
        return productRepository.findAllByPriceBetween(min, max);
    }

    public List<Product> findAllHigherThanPrice(Integer min){
        return productRepository.findAllHigherThanPrice(min);
    }

    public List<Product> findAllLowerPrice(Integer max){
        return productRepository.findAllLowerThanPrice(max);
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
