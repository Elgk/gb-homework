package gb.spring.controller;

import gb.spring.model.Product;
import gb.spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @PostMapping
    public String save(@ModelAttribute Product product){
        productService.save(product);
        return "redirect:/product";
    }
}
