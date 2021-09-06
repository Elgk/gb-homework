package gb.spring.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="client")
@NamedQueries({
        @NamedQuery(name = "Client.findAll",query = "SELECT a.id, a.name from client a"),
        @NamedQuery(name = "Client.findById", query = "SELECT a.id, a.name from client a where a.id = :id")
})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "client_id")
    private List<ClientProducts> productsList;

    public Client(){

    }

    public Client( String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(Integer id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

}
