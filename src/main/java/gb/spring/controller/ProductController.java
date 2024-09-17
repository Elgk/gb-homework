package gb.spring.controller;

import gb.spring.exceptions.ResourseNotFoundException;
import gb.spring.model.Product;
import gb.spring.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/title")
    public List<Product> findAllLikeTitle(@RequestParam String name){
        return productService.findAllLikeTitle(name);
    }

    @GetMapping("/price_between")
    public List<Product> findAllByPriceBetween(@RequestParam(defaultValue = "0") Integer min,
                                               @RequestParam(defaultValue = "1000") Integer max){
        return productService.findAllByPriceBetween(min, max);
    }

    @GetMapping("/min_price")
    public List<Product> findAllHigherThanPrice(@RequestParam(defaultValue = "0") Integer min){
        return productService.findAllHigherThanPrice(min);
    }

    @GetMapping("/max_price")
    public List<Product> findAllLowerThanPrice(@RequestParam(defaultValue = "1000") Integer max){
        return productService.findAllLowerPrice(max);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id).orElseThrow(() ->
                new ResourseNotFoundException(String.format("Product with id=%d not found", id)));
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
