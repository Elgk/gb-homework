package gb.spring.database;

import gb.spring.model.Customer;
import gb.spring.model.Order;
import gb.spring.model.Product;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DbConnection {
    private  SessionFactory factory;

    public  Session getSession() {
        return factory.getCurrentSession();
    }

    @PostConstruct
    public  void init() {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();
        prepareData();
    }

    @PreDestroy
    public  void shutdown(){
        if (factory != null){
            factory.close();
        }
    }
    private void prepareData(){
        try {
            String startSql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
            HibernateAction.executedInTransaction(this, session -> session.createNativeQuery(startSql).executeUpdate());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

