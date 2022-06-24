package gb.spring.model;

import javax.persistence.*;

@Entity
@Table(name="product")
@NamedQueries({
        @NamedQuery(name="Product.findAll", query="SELECT a from Product  a order by a.title"),
        @NamedQuery(name="Product.findById", query="SELECT a from Product a where a.id = :id")
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

    public Product(){
    }

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
