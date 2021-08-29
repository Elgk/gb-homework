package gb.spring.service.context;

public class Product {
    private int id;
    private  String title;
    private long cost;

    public Product(){
    };

    public Product(int id, String title, long cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getCost() {
        return cost;
    }

    public String toString(){
        String text = id +" " + title + "   "+ cost;
        return text;
    }
}
