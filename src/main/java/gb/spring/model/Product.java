package gb.spring.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
@NamedQueries({
        @NamedQuery(name="Product.findAll", query="SELECT a from Product  a order by a.title"),
        @NamedQuery(name="Product.findById", query="SELECT a from Product a  where a.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private int price;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    public Product(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", price=" + price + '\'' +
                '}';
    }
}
