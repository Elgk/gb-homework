package gb.spring.repository.specifications;

import gb.spring.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> priceBetween(Integer min, Integer max){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), min, max));
    }

    public static  Specification<Product> nameEqual(String name){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name));
    }

    public static Specification<Product> nameLike(String name){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%",  name)));
    }
}
