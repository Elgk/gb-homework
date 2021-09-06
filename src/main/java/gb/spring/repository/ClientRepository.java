package gb.spring.repository;

import gb.spring.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClientRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public ClientRepository(){
        this.session = sessionFactory.getCurrentSession();
    }

    public List<Client> findAll() {
        session.beginTransaction();
        List<Client> clientList = session.createNamedQuery("Client.findAll", Client.class).getResultList();
        session.getTransaction().commit();
        return clientList.stream().collect(Collectors.toUnmodifiableList());
    }

    public Optional<Client> findById(Integer id){
        session.beginTransaction();
        Optional<Client>  client = Optional.ofNullable(session.createNamedQuery("Client.findById", Client.class)
                .setParameter("id", id)
                .getSingleResult());
        return client;
    }

    public void saveClient(Client client){
        session.beginTransaction();
        Client newClient = new Client(client.getName());
        session.save(newClient);
        session.getTransaction().commit();

    }
}
