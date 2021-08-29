package gb.spring.client;

import gb.spring.service.context.Cart;
import gb.spring.service.context.ProductRepository;
import org.springframework.context.ApplicationContext;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private final ApplicationContext context;
    private ProductRepository productRepository;
    private Cart cart;
    private final Scanner scanner = new Scanner(System.in);

    public Client(ApplicationContext context){
        this.context = context;
        productRepository = context.getBean(ProductRepository.class);
    }

    public void run(){
        System.out.println("Wellcome!");
        showHelp();
        while (true) {
            Commands command = readCommand();
            switch (command) {
                case EXIT:
                    scanner.close();
                    return;
                case HELP:
                    showHelp();
                    break;
                case LIST:
                    showProductsList();
                    break;
                case NEW:
                    newCart();
                    break;
                case ADD:
                    cartAction(command);
                    break;
                case DEL:
                    cartAction(command);
                    break;
                case CLEAR:
                    clearCart();
                    break;
                case SHOW:
                    showCart();
                    break;
            }
        }
    }

    private void showHelp(){
        System.out.println("Список комманд для работы с корзиной: \n"+
                "HELP -  справка;\n"+
                "LIST -  список доступных товаров;\n"+
                "NEW -   создать новую корзину;\n"+
                "SHOW -  показать корзину\n"+
                "ADD -   добавить товар\n"+
                "DEL -   удалить товар\n"+
                "CLEAR - очистить корзину\n"+
                "EXIT -  выход");
    }

    private void showProductsList(){
        System.out.println("Список товаров");
        System.out.println("ID Title  Price");
        productRepository.getList().stream().forEach(System.out::println);
    }

    private void newCart(){
        cart = context.getBean(Cart.class);
        System.out.println("Корзина создана, добавьте товар.");
    }
    private void showCart(){
        if (cart == null){
            System.out.println("Корзина отсутствует");
        }else {
            System.out.println(cart.viewCart());
        }
    }

    private void cartAction(Commands command){
        if (cart == null){
            System.out.println("Сначала создайте корзину");
            return;
        }
        try{
            int id = readProductId();
            switch (command){
                case ADD:
                    cart.addProduct(productRepository.getProduct(id));
                    break;
                case DEL:
                    cart.removeProduct(productRepository.getProduct(id));
                    break;
            }
            showCart();
        }catch (NoSuchElementException e){
            System.out.println("Такой ID не существует");
        }catch (NullPointerException e){
            System.out.println("Удаление не выполнено: неверно указан товар");
        }
    }
    private int readProductId(){
        int in;
        System.out.println("Введите ID товара: ");
        while (true){
            try{
                in = Integer.parseInt(scanner.nextLine());
                return in;
            }catch (NumberFormatException e){
                System.out.println("Неверный ввод: ID товара - число");
                continue;
            }
        }
    }
    private  Commands readCommand(){
        String inputString = "";
        while (true){
            try{
               System.out.println("Введите команду: ");
               inputString = scanner.nextLine();
               return Commands.valueOf(inputString);
            }catch (IllegalArgumentException e){
                System.out.println("Неверная команда: " + inputString);
                continue;
            }
        }
    }

    private void clearCart(){
        cart.removeAll();
        showCart();
    }
}
