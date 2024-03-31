package mk.ukim.finki.ecommmerceapp.service;

import mk.ukim.finki.ecommmerceapp.model.Product;
import mk.ukim.finki.ecommmerceapp.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
 List<Product> listAllProductsInShoppingCart(Long cartId);
 ShoppingCart getActiveShoppingCart(String username);
 ShoppingCart addProductToShoppingCart(String username,Long productId);

}
