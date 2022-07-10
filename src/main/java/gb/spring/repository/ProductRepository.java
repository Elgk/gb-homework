package gb.spring.repository;

import gb.spring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.price between :min and :max")
    List<Product> findAllByPriceBetween(Integer min, Integer max);

    @Transactional
    @Modifying
    @Query("update Product p set p.price = :price where p.id = :id")
    void changePriceById(Long id, Integer price);

}
