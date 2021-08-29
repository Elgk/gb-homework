package gb.spring.client;

import gb.spring.service.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AppMain {

    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        new Client(context).run();
        System.out.println("Good bye!");

    }
}
