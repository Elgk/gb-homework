package gb.spring.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
@NamedQueries({
        @NamedQuery(name = "Customer.findAll",query = "SELECT a from Customer a"),
        @NamedQuery(name = "Customer.findById", query = "SELECT a from Customer a where a.id = :id")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(){
    }

    public Customer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return name;
    }
}
