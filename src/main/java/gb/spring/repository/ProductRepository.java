package gb.spring.repository;

import gb.spring.database.DbConnection;
import gb.spring.database.HibernateAction;
import gb.spring.model.Order;
import gb.spring.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private final DbConnection dbConnection;

    public ProductRepository(DbConnection dbConnection){
       this.dbConnection = dbConnection;
    }

    public List<Product> findAll(){
        List<Product> productList = HibernateAction.executedInTransactionWithResult(dbConnection,
                 ss -> ss.createNamedQuery("Product.findAll", Product.class)
                         .getResultList());
        return productList.stream().collect(Collectors.toUnmodifiableList());
    }

    public String findById(Long id){
        return HibernateAction.executedInTransactionWithResult(dbConnection,
                session -> {
                    Optional<Product> pp = Optional.ofNullable(session.createNamedQuery("Product.findById", Product.class)
                            .setParameter("id", id)
                            .getSingleResult());

                    StringBuilder sb = new StringBuilder();
                    for (Order order : pp.get().getOrders()) {
                        sb.append(order.getCustomer().toString()).append(", ")
                                .append(order).append("<br/>");
                    }
                    return pp.get() + ": " + "<br/>" + sb;
                });
    }

    public void saveProduct(Product product){
        HibernateAction.executedInTransaction(dbConnection, ss -> ss.saveOrUpdate(product));
    }

    public void deleteByID(Long id){
        HibernateAction.executedInTransaction(dbConnection,
                ss -> ss.createQuery("delete from Product where id = :id")
                        .setParameter("id", id)
                        .executeUpdate());
    }
}
