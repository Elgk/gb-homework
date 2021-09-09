package gb.spring.controller;

import gb.spring.model.Order;
import gb.spring.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private  final OrderService orderService;

    @GetMapping("/findByName/{name}")
    public List<Order> findByName(@PathVariable String name){
        return orderService.findByName(name);
    }

    @GetMapping("/findById/{id}")
    public List<Order> findById(@PathVariable Integer id){
        return orderService.findByClientId(id);
    }

    @GetMapping("/findAll")
    public List<Order> findAll(){
        return orderService.findAll();
    }
}
