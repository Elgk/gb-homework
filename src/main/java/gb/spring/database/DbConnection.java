package gb.spring.database;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@RequiredArgsConstructor
public class DbConnection {
    private static SessionFactory factory;
    private static Session session = null;


    public static Session getSession() {
        return session;
    }

    public static SessionFactory getFactory() {
        return factory;
    }

    @PostConstruct
    public static void init() {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
        session = factory.getCurrentSession();
        PrepareDataApp.forcePrepareData();
    }

    @PreDestroy
    public static void shutdown(){
        factory.close();
        if (session != null){
            session.close();
        }
    }

}

