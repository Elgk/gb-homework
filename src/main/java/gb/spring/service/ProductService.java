package gb.spring.service;

import gb.spring.dto.ProductDto;
import gb.spring.model.Product;
import gb.spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDto> findAll(){
        return productRepository.findAll().stream()
                .map(ProductDto::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    public void save(ProductDto productDto) {
        productRepository.save(productDto.mapToProduct());
    }

    public void updateProuct(Long id, ProductDto productDto){
        if (productRepository.existsById(id)){
            Product updateProduct = productRepository.getById(id);
            productRepository.save(productDto.updProduct(updateProduct));
        }else {
            throw new NoSuchElementException();
        }
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

}
