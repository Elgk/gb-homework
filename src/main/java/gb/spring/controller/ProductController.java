package gb.spring.controller;

import gb.spring.dto.ProductDto;
import gb.spring.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
@Api(value = "контроллер для работы с товаром")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @ApiOperation(value = "список товаров")
    public List<ProductDto> findAll(){
        return productService.findAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "сохранение нового товара")
    public void save(@RequestBody ProductDto productDto){
        productService.save(productDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "удаление товара")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ApiOperation(value = "изменение товара")
    public void saveUpd(@PathVariable  Long id, @RequestBody ProductDto productDto)    {
        productService.updateProuct(id, productDto);
    }
}
