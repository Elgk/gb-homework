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

    public List<ProductDto> findAllByPriceBetween(Integer min, Integer max){
        return productRepository.findAllByPriceBetween(min, max).stream()
                .map(ProductDto::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    public void save(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setId(productDto.getId());
        productRepository.save(product);

//        if (productRepository.existsById(productDto.getId())){
//            Product mp = productRepository.getById(productDto.getId());
//            productRepository.save(productDto.updProduct(mp));
//        }else {
//            productRepository.save(productDto.mapToProduct());
//        }

    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void changePriceById(Long id, Integer price){
        productRepository.changePriceById(id, price);
    }

}
