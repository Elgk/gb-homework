package gb.spring.controller;

import gb.spring.dto.ProductDto;
import gb.spring.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Api(value = "контроллер для работы с товаром")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @ApiOperation(value = "список товаров")
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(required = false) String namePart,
                                    @RequestParam(required = false) Integer min,
                                    @RequestParam(required = false) Integer max){
        return productService.findAll(page, namePart, min, max)
                .map(s -> ProductDto.valueOf(s));
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id){
        return ProductDto.valueOf(productService.findById(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "сохранение нового товара")
    public void create(@RequestBody ProductDto productDto){
        productService.save(productDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "удаление товара")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ApiOperation(value = "изменение товара")
    public void update( @RequestBody ProductDto productDto)    {
        productService.update(productDto);
    }
}
