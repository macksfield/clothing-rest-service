package max.engineering.clothingrestservice.Product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import max.engineering.clothingrestservice.Product.ExceptionHandling.ProductNotFoundException;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository repository;

    private Product existingProduct;

    @TestConfiguration
    static class ProductServiceContextConfig {

        @Bean
        public ProductService getProductService() {
            return new ProductService();
        }
    }

    @BeforeEach
    public void setUp() {
        existingProduct = new Product("Green Hat", "Red hugo boss shirt", "Hugo Boss",
                Arrays.asList("red", "shirt", "slim fit"), "apparel");

    }

    @Test
    void deleteNotExistingProductTest() {
        Mockito.when(repository.findById((long) 100)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteProduct((long) 100);
        });
    }

    @Test
    void deleteExistingProductTest() {

        Mockito.when(repository.findById((long) 2)).thenReturn(Optional.of(existingProduct));
        assertDoesNotThrow(() -> productService.deleteProduct((long) 2));
    }

    @Test
    void findByIdNotExistingProduct() {

        Mockito.when(repository.findById((long) 100)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.findProduct((long) 100));
    }

}