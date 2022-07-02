package gb.spring.controller;

import gb.spring.model.Customer;
import gb.spring.model.Product;
import gb.spring.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/product/{id}")
    @ResponseBody
    public String findCustomersByProductId(@PathVariable Long id){
        return orderService.findCustomersByProductId(id);
           }

    @GetMapping("/customer/{id}")
    @ResponseBody
    public String getProductsByCustomerId(@PathVariable Long id){
        return orderService.findProductsByCustomerId(id);
    }
}
