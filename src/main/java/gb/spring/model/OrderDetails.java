package gb.spring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="order_details")
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="order_id")
    private Integer order_id;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private Integer price;
}
