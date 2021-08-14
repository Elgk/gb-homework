package gb.spring;

public class Product {
    private int id;
    private String title;
    private long cost;

    public Product(int id, String title, long cost){
        this.title = title;
        this.cost = cost;
        this.id = id;
    }

    public String toString(){
        return title + "    -    " + cost;
    }
}
