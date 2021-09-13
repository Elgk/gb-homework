package gb.spring.service;

import gb.spring.dto.ProductDto;
import gb.spring.model.Product;
import gb.spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if (productRepository.existsById(productDto.getId())){
            Product mp = productRepository.getById(productDto.getId());
            productRepository.save(productDto.updProduct(mp));
        }else {
            productRepository.save(productDto.mapToProduct());
        }

    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

}
