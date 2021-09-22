package max.engineering.clothingrestservice.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    List<Product> all() {
        return productService.all();
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return productService.newProduct(newProduct);
    }

    @GetMapping("/products/category")
    public ResponseEntity<List<Product>> findByCategory(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "apparel") String category) {
        return productService.findByCategory(page, size, category);
    }

    @GetMapping("/products/{id}")
    Product one(@PathVariable Long id) {
        return productService.one(id);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) {

        productService.updateProduct(product);

        return product;
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}