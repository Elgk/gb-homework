package gb.spring.service;

import gb.spring.dto.ProductDto;
import gb.spring.exceptions.ResourceNotFoundException;
import gb.spring.model.Product;
import gb.spring.repository.ProductRepository;
import gb.spring.repository.specifications.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final int PAGE_SIZE = 5;

    public Page<Product> findAll(Integer page, String namePart, Integer min, Integer max){
        Specification<Product> spec = Specification.where(null);
        if (namePart != null){
           spec = spec.and(ProductSpecification.nameLike(namePart));
        }
        if (min == null){
            min = 1;
        }
        if (max == null){
            max = 100000;
        }
        spec = spec.and(ProductSpecification.priceBetween(min, max));
        if (page < 1){
            page = 1;
        }
        return productRepository.findAll(spec, PageRequest.of(page-1, PAGE_SIZE));
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id = " +id + " not found"));
    }

    public void save(ProductDto productDto) {
        productRepository.save(productDto.mapToProduct());
    }

    public void update(ProductDto productDto){
        Long productId = productDto.getId();
        if (productRepository.existsById(productId)){
           // Product updateProduct = productRepository.getById(id);
            productRepository.save(productDto.mapToProduct());
        }else {
            throw new ResourceNotFoundException("Product with id = " + productId + " not found");
        }
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

}
