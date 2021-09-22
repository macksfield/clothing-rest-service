package max.engineering.clothingrestservice.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductIntegrationTest {

    @Autowired
    private ProductRepository repository;
    public Long currentId;

    @BeforeEach
    public void createProduct() {
        Product product = new Product("Green Hat", "Red hugo boss shirt", "Hugo Boss",
                Arrays.asList("red", "shirt", "slim fit"), "apparel");

        currentId = repository.save(product).getId();
    }

    @Test
    void showProducts() {
        List<Product> productList = repository.findAll();
        assertThat(productList).size().isGreaterThan(0);
    }

    @Test
    void createNewProductTest() {
        Product product = new Product("Green Hat", "Red hugo boss shirt", "Hugo Boss",
                Arrays.asList("red", "shirt", "slim fit"), "apparel");

        Product newOne = repository.save(product);
        assertNotNull(newOne);
    }

    @Test
    void findProductByIdExistingTest() {
        Optional<Product> product = repository.findById(currentId);
        assertThat(product.orElse(null)).isNotNull();
    }

    @Test
    void deleteExistingProductTest() {

        boolean isPresentBeforeDelete = repository.findById(currentId).isPresent();
        repository.deleteById(currentId);
        boolean notPresentAfterDelete = repository.findById(currentId).isPresent();

        assertTrue(isPresentBeforeDelete);
        assertFalse(notPresentAfterDelete);

    }
}