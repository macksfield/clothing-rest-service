package max.engineering.clothingrestservice.Product;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Product {

    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private String brand;
    @ElementCollection(targetClass = String.class)
    private List<String> tags;
    private String category;
    @CreationTimestamp
    protected Timestamp creationDate;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, String description, String brand, List<String> tags, String category) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.tags = tags;
        this.category = category;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getBrand() {
        return this.brand;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public String getCreatedDate() {
        return sdf.format(this.creationDate);
    }

    public String getCategory() {
        return this.category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}