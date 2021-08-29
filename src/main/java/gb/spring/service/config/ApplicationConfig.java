package gb.spring.service.config;

import gb.spring.service.context.Cart;
import gb.spring.service.context.ProductRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "gb.spring")
public class ApplicationConfig {
    @Bean
    public ProductRepository productRepository(){
        return new ProductRepository();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Cart cart(){ return new Cart(); };

}
