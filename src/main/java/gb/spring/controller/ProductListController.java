package gb.spring.controller;

import gb.spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/list")
@RequiredArgsConstructor
public class ProductListController {
    private final ProductService productService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model){
        model.addAttribute("products", productService.findById(id));
        return "products";
    }
}
