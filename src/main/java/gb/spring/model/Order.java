package gb.spring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="client_id")
    private Integer client_id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client name;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderDetails> title;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderDetails> price;
}