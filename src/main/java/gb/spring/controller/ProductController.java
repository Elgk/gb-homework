package gb.spring.controller;

import gb.spring.model.Product;
import gb.spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @GetMapping("/find/{id}")
    public String findById(@PathVariable Integer id, Model model){
        model.addAttribute("products", productService.findById(id));
        return "product";
    }

    @PostMapping
    public String save(@ModelAttribute Product product){
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}")
    public String deleteById(@PathVariable Integer id) {
        productService.deleteByID(id);
        return "redirect:/product";
    }
}
