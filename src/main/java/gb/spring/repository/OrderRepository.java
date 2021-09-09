package gb.spring.repository;

import gb.spring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Override
    List<Order> findAll();

    List<Order> findAllByClient_id(Integer client_id);

    @Query(value = "select d.id, d.client_id, c.name from orders d join client c where d.client_id = c.id and c.name = :name", nativeQuery = true)
    List<Order> findByName(String name);

    @Query(value = "select d.id, d.client_id, c.name, s.title, s.price " +
            "         from orders d join order_details s " +
            "          join client c "+
            "         where d.id = s.order_id and d.client_id = c.id and d.id = :id",
            nativeQuery = true)
    Optional<Order> findById(Integer id);
}
