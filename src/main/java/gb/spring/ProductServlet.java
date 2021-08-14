package gb.spring;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Product> productList = new CopyOnWriteArrayList<>();
        Random random = new Random();
        long price;
        for (int i = 0; i < 10; i++) {
            do{
                price = random.nextInt(5000);
            }while (price < 500);
            productList.add(new Product(i,"dress"+(i+1), price));
        }
        resp.getWriter().write("<h3>title         price</h3> <br/>");

        for (Product product : productList) {
            resp.getWriter().write(product.toString()+"<br/>");
        }

    }
}
