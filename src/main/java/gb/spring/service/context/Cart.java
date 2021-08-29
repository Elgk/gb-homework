package gb.spring.service.context;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {
    private Map<Product, Integer> cart;

    public Cart(){
        this.cart  = new ConcurrentHashMap<>();
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void addProduct(Product product){
        int qnt = cart.getOrDefault(product,0);
        cart.put(product, qnt + 1);
    }

    public void removeProduct(Product product)  {
        int qnt = cart.get(product);
        if (qnt - 1 == 0) {
            cart.remove(product);
        } else {
            cart.put(product, qnt - 1);
        }
    }
    public void removeAll(){
        cart.clear();
    }

    public String viewCart(){
        if (cart.size() == 0){
            return "Ваша корзина пуста";
        }
        StringBuilder sb = new StringBuilder();
        long totalSum = 0;
        for (Map.Entry<Product, Integer> entry: cart.entrySet() ) {
            int id = entry.getKey().getId();
            long price = entry.getKey().getCost();
            int qnt = entry.getValue();
            long sum =  price * qnt;
            String title = entry.getKey().getTitle();
            sb.append(id).append(" ").append(title).append(", ").append(price).append("р, ").append("кол-во ").append(qnt).append(", сумма " +sum);
            sb.append(System.lineSeparator());
            totalSum += price * qnt;
        }
        sb.append("итого: " + totalSum);
        return sb.toString();
    }
}
