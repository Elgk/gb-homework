package gb.spring.integration;

import gb.spring.dto.ProductDto;
import gb.spring.model.Product;
import gb.spring.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductControllerIntegrationTest {
    private static final String BASE_URL = "/workout/api/v1/products";

    @Autowired
    private ProductRepository productRepository;

    @LocalServerPort
    private String port;

    @Autowired
    private TestRestTemplate testTemplate;

    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
    }

    @Test
    public void findByIdSuccess(){
        Product product = new Product();
        product.setPrice(100);
        product.setTitle("test");
        Product savedProduct = productRepository.save(product);
        ResponseEntity<ProductDto> actual = testTemplate.getForEntity("http://localhost:" + port + BASE_URL + "/" +savedProduct.getId(), ProductDto.class);
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actual.getBody()).isNotNull();
    }

    @Test
    public void findByIdFailed(){
        ResponseEntity<ProductDto> actual = testTemplate.getForEntity("http://localhost:" + port + BASE_URL + "/1" , ProductDto.class);
        // Assertions.assertThat(actual.getBody()).isNotNull();// тест не пройдет
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
}
