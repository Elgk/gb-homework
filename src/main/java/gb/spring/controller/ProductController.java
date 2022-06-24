package gb.spring.controller;

import gb.spring.model.Product;
import gb.spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("products", productService.findAll());
        return "product"; // это название страницы product.html , ее эндпоинт - /products
    }

    @GetMapping("/find/{id}")
    public String findById(@PathVariable Long id, Model model){
        model.addAttribute("products", productService.findById(id));
        return "product";
    }

    @PostMapping
    public String save(@ModelAttribute Product product){
        productService.save(product);
        return "redirect:/products"; // перевод на ЭНДПОИНТ страницы product.html
    }

    @GetMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        productService.deleteByID(id);
        return "redirect:/products";
    }

}
