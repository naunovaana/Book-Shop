package mk.ukim.finki.ecommmerceapp.service.impl;

import mk.ukim.finki.ecommmerceapp.model.Product;
import mk.ukim.finki.ecommmerceapp.model.ShoppingCart;
import mk.ukim.finki.ecommmerceapp.model.User;
import mk.ukim.finki.ecommmerceapp.model.enumeration.ShoppingCartStatus;
import mk.ukim.finki.ecommmerceapp.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.ecommmerceapp.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.ecommmerceapp.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.ecommmerceapp.model.exceptions.UserNotFoundException;
import mk.ukim.finki.ecommmerceapp.repository.ShoppingCartRepository;
import mk.ukim.finki.ecommmerceapp.repository.UserRepository;
import mk.ukim.finki.ecommmerceapp.service.ProductService;
import mk.ukim.finki.ecommmerceapp.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);

        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user=this.userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));

        return this.shoppingCartRepository.findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(()->{
                    ShoppingCart cart=new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart=this.getActiveShoppingCart(username);
        Product product=this.productService.findById(productId).orElseThrow(()->new ProductNotFoundException(productId));
        if(shoppingCart.getProducts()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInShoppingCartException(productId, username);
        shoppingCart.getProducts().add(product);

        return this.shoppingCartRepository.save(shoppingCart);
    }
}
