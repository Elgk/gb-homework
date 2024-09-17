package gb.spring.controller;

import gb.spring.model.Order;
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
        return "product";
    }

    // выводит список покупателей с детализацией по заказам (один заказ - один продукт)
    @GetMapping("/find/{id}")
    @ResponseBody
    public String findById(@PathVariable Long id, Model model){
         return productService.findById(id);
    }

    @PostMapping
    public String save(@ModelAttribute Product product){
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        productService.deleteByID(id);
        return "redirect:/product";
    }

}
