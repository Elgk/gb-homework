package gb.spring.repository;

import gb.spring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetween(Integer min, Integer max);

    @Query("select p from Product p where p.price >= ?1 order by p.price")
    List<Product> findAllHigherThanPrice(Integer min);

    @Query("select p from Product p where p.price <= ?1 order by p.price")
    List<Product> findAllLowerThanPrice(Integer max);
}
