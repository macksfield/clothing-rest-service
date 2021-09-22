package max.engineering.clothingrestservice.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import max.engineering.clothingrestservice.Product.ExceptionHandling.ProductNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> all() {
        return repository.findAll();
    }

    public Product newProduct(@RequestBody Product newProduct) {
        return repository.save(newProduct);
    }

    public Product one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public Product findProduct(Long id) {
        Optional<Product> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ProductNotFoundException(id);
        } else
            return optional.get();
    }

    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    public ResponseEntity<List<Product>> findByCategory(int page, int size, String category) {
        try {
            Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));

            Page<Product> prodPage = repository.findByCategory(category, paging);

            return new ResponseEntity<>(prodPage.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}