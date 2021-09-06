package gb.spring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="client_products")
@Data
public class ClientProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "client_id")
    private Integer clientID;

    @Column(name="product_id")
    private Integer productID;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "order")
    private Integer order;

}
