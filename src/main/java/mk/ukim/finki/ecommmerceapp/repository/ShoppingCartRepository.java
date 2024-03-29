package mk.ukim.finki.ecommmerceapp.repository;

import mk.ukim.finki.ecommmerceapp.model.ShoppingCart;
import mk.ukim.finki.ecommmerceapp.model.User;
import mk.ukim.finki.ecommmerceapp.model.enumeration.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus created);

    Optional<ShoppingCart> findByUserUsernameAndStatus(String username, ShoppingCartStatus created);

    List<ShoppingCart> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}
