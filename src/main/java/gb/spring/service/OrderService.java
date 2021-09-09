package gb.spring.service;

import gb.spring.model.Order;
import gb.spring.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    public List<Order> findByName(String name){
        return orderRepository.findByName(name);
    }

    public List<Order> findByClientId(Integer id){
        return orderRepository.findAllByClient_id(id);
    }

}
