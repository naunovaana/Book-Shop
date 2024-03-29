package mk.ukim.finki.ecommmerceapp.service;

import mk.ukim.finki.ecommmerceapp.model.Product;
import mk.ukim.finki.ecommmerceapp.model.ShoppingCart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
     List<Product>  listAllProductsInShoppingCart(Long cartId);
     ShoppingCart getActiveShoppingCart(String username);
     ShoppingCart addProductsToShoppingCart(String username,Long productId);
     List<ShoppingCart> findAll();
     ShoppingCart save(ShoppingCart shoppingCart);
     Optional<ShoppingCart> findById(Long id);
    Long countSuccessfulOrders(String username);
    List<ShoppingCart> filterByDateTimeBetween(LocalDateTime from, LocalDateTime to);

}
