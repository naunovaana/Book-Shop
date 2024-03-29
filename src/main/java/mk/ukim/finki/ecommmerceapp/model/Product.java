package mk.ukim.finki.ecommmerceapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Author author;



    public Product(String name, Double price, Integer quantity, Category category, Author author) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.author = author;
    }


}
