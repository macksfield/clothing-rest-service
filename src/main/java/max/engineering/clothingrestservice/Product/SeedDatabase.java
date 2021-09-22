package max.engineering.clothingrestservice.Product;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SeedDatabase {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {

        return args -> {
            repository.save(new Product("Green Hat", "Red hugo boss shirt", "Hugo Boss",
                    Arrays.asList("red", "shirt", "slim fit"), "apparel"));
            repository.save(new Product("Blue Pants", "Very soft", "Fancy Brand",
                    Arrays.asList("blue", "pants", "slim fit"), "pants"));
        };
    }
}