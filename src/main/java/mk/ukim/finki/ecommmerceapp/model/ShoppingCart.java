package mk.ukim.finki.ecommmerceapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.ecommmerceapp.model.enumeration.ShoppingCartStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;
    @ManyToMany
    private List<Product> products;

    @ManyToOne
    private User user;


    public ShoppingCart(User user) {
        this.status = ShoppingCartStatus.CREATED;
        this.dateCreated = LocalDateTime.now();
        this.products = new ArrayList<>();
        this.user=user;
    }
}
