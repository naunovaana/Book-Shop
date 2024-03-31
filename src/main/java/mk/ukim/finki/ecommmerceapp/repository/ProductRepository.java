package mk.ukim.finki.ecommmerceapp.repository;

import mk.ukim.finki.ecommmerceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
    void deleteByName(String name);
    List<Product> findAllByNameLike(String name);


}
