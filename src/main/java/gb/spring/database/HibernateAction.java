package gb.spring.database;

import org.hibernate.Session;

import java.util.function.Consumer;
import java.util.function.Function;

public class HibernateAction {

    public static void executedInTransaction(DbConnection dbConnection, Consumer<Session> consumer){
        Session session = dbConnection.getSession();
        session.beginTransaction();
        try {
            consumer.accept(session);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static<T> T executedInTransactionWithResult(DbConnection dbConnection, Function<Session, T> function){
        Session session = dbConnection.getSession();
        session.beginTransaction();
        try{
            T result = function.apply(session);
            session.getTransaction().commit();
            return result;
        }catch (Exception e){
            session.getTransaction().rollback();
            throw e;
        }
    }
}
