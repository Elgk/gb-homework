package gb.spring.repository;

import gb.spring.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HiberConfigUtils {
    private SessionFactory sessionFactory;

    public HiberConfigUtils(){
    }

    @PostConstruct
    public void init(){
        Configuration configuration = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .addAnnotatedClass(Product.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void shutdown(){
        if (sessionFactory != null){
            sessionFactory.close();
        }
    }

}
