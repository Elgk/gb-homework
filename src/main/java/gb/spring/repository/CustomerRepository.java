package gb.spring.repository;

import gb.spring.database.DbConnection;
import gb.spring.database.HibernateAction;
import gb.spring.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {
    private final DbConnection dbConnection;

    public CustomerRepository(DbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public List<Customer> findAll() {
        List<Customer> customerList = HibernateAction.executedInTransactionWithResult(dbConnection,
                session -> session.createNamedQuery("Customer.findAll", Customer.class)
                        .getResultList());
        return customerList.stream().collect(Collectors.toUnmodifiableList());
    }

    public Optional<Customer> findById(Long id){
       return  HibernateAction.executedInTransactionWithResult(dbConnection,
                session -> Optional.ofNullable(session.createNamedQuery("Customer.findById", Customer.class)
                        .setParameter("id", id)
                        .getSingleResult()));
    }

    public void saveCustomer(Customer customer){
        HibernateAction.executedInTransaction(dbConnection, session -> session.saveOrUpdate(customer));
    }
}
