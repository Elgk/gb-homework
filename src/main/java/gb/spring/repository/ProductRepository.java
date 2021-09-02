package gb.spring.repository;

import gb.spring.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private static SessionFactory factory;
    private static Session session = null;

    public static void init() {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
        session = factory.getCurrentSession();
    }
    public static void shutdown(){
        factory.close();
        if (session != null){
            session.close();
        }
    }

    public ProductRepository(){
        init();
    }

    public List<Product> findAll(){

        session.beginTransaction();
        List<Product> productList = session.createNamedQuery("Product.findAll", Product.class).getResultList();
        session.getTransaction().commit();
        return productList.stream().collect(Collectors.toUnmodifiableList());
    }

    public Optional<Product> findById(Integer id){
        session.beginTransaction();
        Optional<Product>  product = Optional.ofNullable(session.createNamedQuery("Product.findById", Product.class)
                .setParameter("id", id)
                .getSingleResult());
        return product;

    }

    public void saveProduct(Product product){
        session.beginTransaction();
        Product newProduct = new Product(product.getTitle(), product.getCost());
        session.save(newProduct);
        session.getTransaction().commit();

    }

    public void deleteByID(Integer id){
        session.beginTransaction();
        Product product = session.get(Product.class,id);
        session.delete(product);
        session.getTransaction().commit();

    }
}
