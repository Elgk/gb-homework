package gb.spring.service;

import gb.spring.model.Customer;
import gb.spring.model.Product;
import gb.spring.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String findProductsByCustomerId(Long customerId){
        return orderRepository.findByCustomerId(customerId);
    }

    public String findCustomersByProductId(Long productId){
       return orderRepository.getCustomerListByProduct(productId);
    }
}
