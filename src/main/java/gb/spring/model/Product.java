package gb.spring.model;

import javax.persistence.*;

@Entity
@Table(name="product")
@NamedQueries({
        @NamedQuery(name="Product.findAll", query="SELECT a.id, a.title, a.cost from product a order by a.title"),
        @NamedQuery(name="Product.findById", query="SELECT a.id, a.title, a.cost from product a where a.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="title")
    private String title;
    @Column(name="cost")
    private Long cost;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getCost() {
        return cost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
    public Product(){

    }

    public Product(String title, Long cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(Integer id, String title, Long cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

}
