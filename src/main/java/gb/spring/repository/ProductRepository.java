package gb.spring.repository;

import gb.spring.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements ProductDao{
    private final HiberConfigUtils hiberConfigUtils;


    public ProductRepository(HiberConfigUtils hiberConfigUtils) {
        this.hiberConfigUtils = hiberConfigUtils;
    }

    @Override
    public List<Product> findAll(){
        Session session = hiberConfigUtils.getSession();
        session.beginTransaction();
        List<Product> productList = session.createNamedQuery("Product.findAll", Product.class).getResultList();
        session.getTransaction().commit();
        return productList.stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<Product> findById(Long id){
        Session session = hiberConfigUtils.getSession();
        session.beginTransaction();
        Optional<Product>  product = Optional.ofNullable(session.createNamedQuery("Product.findById", Product.class)
                .setParameter("id", id)
                .getSingleResult());
        session.getTransaction().commit();
        return product;
    }

    @Override
    public void saveProduct(Product product){
        Session session = hiberConfigUtils.getSession();
        session.beginTransaction();
        session.saveOrUpdate(product);
      //  Product newProduct = new Product(product.getTitle(), product.getPrice());
      //  session.save(newProduct);
        session.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id){
        Session session = hiberConfigUtils.getSession();
        session.beginTransaction();
        session.createQuery("delete from Product p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
    }
}
