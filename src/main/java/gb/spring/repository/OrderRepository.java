package gb.spring.repository;

import gb.spring.database.DbConnection;
import gb.spring.database.HibernateAction;
import gb.spring.model.Customer;
import gb.spring.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    private final DbConnection dbConnection;

    public OrderRepository(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // List<Product> не проходит из-за lazy initialize order.product (см коментарий ниже)
 /*   public List<Customer> findByProductId(Long productId){
        List<Customer> customerList = HibernateAction.executedInTransactionWithResult(dbConnection,
                session -> session.createQuery("select o.customer from Order o where o.product.id = :productId", Customer.class)
                        .setParameter("productId", productId)
                        .getResultList());
        return customerList;
    }*/

    // для отображения через controller, на выходе нужна строка, т.к. в стоке не отображаем внутренние списки продукта или плкупателя
    // считываем  внутри сессии в строку, т.к. выборка внутренних списков  идет по lazy initialize
    // вывод списком List данных сущностей не проходит из-за внутренних списков Customer.orders/(Product.orders) lazy initialize

    public String findByCustomerId(Long customerId){
        return HibernateAction.executedInTransactionWithResult(dbConnection,
                session -> {
                    List<Product> list = session.createQuery("select o.product from Order o where o.customer.id = :customerId", Product.class)
                            .setParameter("customerId", customerId)
                            .getResultList();
                    return String.join(" ", list.toString());
                });
    }

    public String getCustomerListByProduct(Long productId){
        return HibernateAction.executedInTransactionWithResult(dbConnection,
                ss -> {
                        List<Customer> list = ss.createQuery("select o.customer from Order o where o.product.id = :productId", Customer.class )
                                .setParameter("productId", productId)
                                .getResultList();
                        return String.join(" ", list.toString());
                });
    }
}
