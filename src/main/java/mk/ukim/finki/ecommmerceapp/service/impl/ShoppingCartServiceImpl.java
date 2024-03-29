package mk.ukim.finki.ecommmerceapp.service.impl;

import mk.ukim.finki.ecommmerceapp.model.Product;
import mk.ukim.finki.ecommmerceapp.model.ShoppingCart;
import mk.ukim.finki.ecommmerceapp.model.User;
import mk.ukim.finki.ecommmerceapp.model.enumeration.ShoppingCartStatus;
import mk.ukim.finki.ecommmerceapp.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.ecommmerceapp.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.ecommmerceapp.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.ecommmerceapp.model.exceptions.UserNotFoundException;
import mk.ukim.finki.ecommmerceapp.repository.ProductRepository;
import mk.ukim.finki.ecommmerceapp.repository.ShoppingCartRepository;
import mk.ukim.finki.ecommmerceapp.repository.UserRepository;
import mk.ukim.finki.ecommmerceapp.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        ShoppingCart shoppingCart=this.shoppingCartRepository.findById(cartId).orElseThrow(()->new ShoppingCartNotFoundException(cartId));

        return shoppingCart.getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return this.shoppingCartRepository.findByUserUsernameAndStatus(username, ShoppingCartStatus.CREATED).
                orElseGet(()->{
                    User user =this.userRepository.findByUsername(username)
                            .orElseThrow(()->new UserNotFoundException(username));
                    ShoppingCart shoppingCart=new ShoppingCart(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductsToShoppingCart(String username, Long productId) {
        Product product=this.productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException(productId));
        ShoppingCart cart=this.getActiveShoppingCart(username);
        List<Product> productsInShoppingCart=cart.getProducts().stream()
                .filter(i->i.getId().equals(productId)).collect(Collectors.toList());

        if (productsInShoppingCart.size() > 0) {
            throw new ProductAlreadyInShoppingCartException(productId, username);
        }
        cart.getProducts().add(product);
        return this.shoppingCartRepository.save(cart);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return this.shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return this.shoppingCartRepository.findById(id);
    }

    @Override
    public Long countSuccessfulOrders(String username) {
        return null;
    }

    @Override
    public List<ShoppingCart> filterByDateTimeBetween(LocalDateTime from, LocalDateTime to) {
        return this.shoppingCartRepository.findByDateCreatedBetween(from,to);
    }
}
