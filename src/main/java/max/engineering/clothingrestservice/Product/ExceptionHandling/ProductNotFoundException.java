package max.engineering.clothingrestservice.Product.ExceptionHandling;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Could not find product " + id);
    }
}