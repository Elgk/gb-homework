package gb.spring.service;

import gb.spring.model.Product;
import gb.spring.repository.ProductDao;
import gb.spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public List<Product> findAll(){
        return productDao.findAll();
    }

    public Product findById(Long id){
        return  productDao.findById(id).orElseThrow(() -> new NoSuchElementException());

    }

    public void save(Product product){
        productDao.saveProduct(product);
//        if (product.getId() != null){
//            productRepository.update(product);
//        }
//        else {
//            productRepository.saveProduct(product);
//        }
    }

    public void deleteByID(Long id){
        productDao.deleteById(id);
    }
}
