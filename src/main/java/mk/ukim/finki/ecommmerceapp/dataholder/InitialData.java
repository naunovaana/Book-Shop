package mk.ukim.finki.ecommmerceapp.dataholder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.ecommmerceapp.model.Author;
import mk.ukim.finki.ecommmerceapp.model.Category;
import mk.ukim.finki.ecommmerceapp.model.Product;
import mk.ukim.finki.ecommmerceapp.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialData {
//    public static List<User> users = null;
    public static List<Category> categories = null;
    public static List<Author> authors = null;
    public static List<Product> products = null;
//    public static List<ShoppingCart> shoppingCarts = null;


    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialData(CategoryRepository categoryRepository, AuthorRepository authorRepository, ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        authors = new ArrayList<>();
        products = new ArrayList<>();
//        users=new ArrayList<>();
//        shoppingCarts=new ArrayList<>();

//        if (userRepository.count() == 0) {
//            users.add(
//                    new User(
//                            "ana.naunova",
//                            passwordEncoder.encode("an"),
//                            "Ana",
//                            "Naunova",
//                            Role.ROLE_USER
//                    ));
//            users.add(
//                    new User(
//                            "ivan.danilov",
//                            passwordEncoder.encode("iv"),
//                            "Ivan",
//                            "Danilov",
//                            Role.ROLE_USER
//                    ));
//            users.add(
//                    new User(
//                            "viktorija.stamenova",
//                            passwordEncoder.encode("vi"),
//                            "Viktorija",
//                            "Stamenova",
//                            Role.ROLE_USER
//                    ));
//            users.add(
//                    new User(
//                            "daniela.naunova",
//                            passwordEncoder.encode("dn"),
//                            "Daniela",
//                            "Naunova",
//                            Role.ROLE_USER
//                    ));
//            users.add(
//                    new User(
//                            "admin",
//                            passwordEncoder.encode("admin"),
//                            "admin",
//                            "admin",
//                            Role.ROLE_ADMIN
//                    )
//            );
//            userRepository.saveAll(users);
//        }



        if (categoryRepository.count() == 0) {
            categories.add(new Category("Thriller", "Thriller category"));
            categories.add(new Category("Romance", "Romance category"));
            categories.add(new Category("Comedy", "Comedy category"));
            categoryRepository.saveAll(categories);
        }

        if (authorRepository.count() == 0) {
            authors.add(new Author("Scarlett St. Clair", "USA"));
            authors.add(new Author("Rebecca Yarros", "USA"));
            authors.add(new Author("Peter Schweizer", "MK"));
            authorRepository.saveAll(authors);
        }

        if (productRepository.count() == 0) {
            List<Category> categoryList = categoryRepository.findAll();
            List<Author> manufacturerList = authorRepository.findAll();

            products.add(new Product("The Woman In the Window", 300d, 10, categoryList.get(0), manufacturerList.get(0)));
            products.add(new Product("The chestnut Man", 450.99, 20, categoryList.get(0), manufacturerList.get(1)));
            products.add(new Product("It Ends with Us", 250d, 56, categoryList.get(1), manufacturerList.get(2)));
            products.add(new Product("The House In The Pines", 590.99, 20, categoryList.get(2), manufacturerList.get(2)));
            productRepository.saveAll(products);
        }
//        if (shoppingCartRepository.count() == 0) {
//            List<Product> productList = productRepository.findAll();
//            List<User> userList = userRepository.findAll();
//
//            ShoppingCart shoppingCart1 = new ShoppingCart(userList.get(0));
//            ShoppingCart shoppingCart2 = new ShoppingCart(userList.get(1));
//            ShoppingCart shoppingCart3 = new ShoppingCart(userList.get(1));
//
//            shoppingCart1.getProducts().add(productList.get(0));
//
//            shoppingCart2.getProducts().add(productList.get(0));
//            shoppingCart2.getProducts().add(productList.get(1));
//            shoppingCart2.getProducts().add(productList.get(2));
//
//            shoppingCart3.getProducts().add(productList.get(0));
//
//            shoppingCart1.setStatus(ShoppingCartStatus.FINISHED);
//            shoppingCart2.setStatus(ShoppingCartStatus.FINISHED);
//            shoppingCart3.setStatus(ShoppingCartStatus.FINISHED);
//
//            shoppingCartRepository.save(shoppingCart1);
//            shoppingCartRepository.save(shoppingCart2);
//            shoppingCartRepository.save(shoppingCart3);
//        }


    }
}
