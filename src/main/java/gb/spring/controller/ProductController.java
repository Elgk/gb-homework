package gb.spring.controller;

import gb.spring.dto.ProductDto;
import gb.spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final int PAGE_INTERVAL = 5;

//    @GetMapping
//    public List<ProductDto> findAll(){
//        return productService.findAll();
//    }

    @GetMapping()
    public List<ProductDto> findAllByPriceBetween(@RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "1") Integer min,
                                                  @RequestParam(defaultValue = "10000") Integer max){
        List<ProductDto> productDtos =  productService.findAllByPriceBetween(min, max);
        if (page < 1){
            page = 1;
        }
        List<ProductDto> productsPage = new ArrayList<>();

        int start = Math.min((page-1)* PAGE_INTERVAL, productDtos.size());
        int finish = Math.min(page * PAGE_INTERVAL, productDtos.size());
        for (int i = start; i < finish; i++) {
            productsPage.add(productDtos.get(i));
        }
        return productsPage ;//productService.findAllByPriceBetween(min, max);
    }

    @PostMapping
    // @RequestBody - означает, что в теле запроса придет объект типа json
    // в spring библиотека jackson преобразует json-объект в java-объект
    public void save(@RequestBody ProductDto productDto){
        productService.save(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @GetMapping("/{id}/{price}")
    public void changePrice(@PathVariable Long id, @PathVariable Integer price){
        productService.changePriceById(id, price);
    }
}
