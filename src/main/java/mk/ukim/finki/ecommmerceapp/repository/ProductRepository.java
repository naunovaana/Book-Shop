package mk.ukim.finki.ecommmerceapp.repository;

import mk.ukim.finki.ecommmerceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
}
