package mk.ukim.finki.ecommmerceapp.dataholder;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import mk.ukim.finki.ecommmerceapp.model.*;
import mk.ukim.finki.ecommmerceapp.model.enumeration.ShoppingCartStatus;
import mk.ukim.finki.ecommmerceapp.repository.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class InitialData {
//    public static List<Category> categories=new ArrayList<>();
//    public static List<User> users=new ArrayList<>();
//    public static List<Product> products=new ArrayList<>();
//    public static List<Author> authors=new ArrayList<>();
//    public static List<ShoppingCart> shoppingCarts=new ArrayList<>();

    public static List<User> users = null;
    public static List<Category> categories = null;
    public static List<Author> authors = null;
    public static List<Product> products = null;
    public static List<ShoppingCart> shoppingCarts = null;


    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    public InitialData(CategoryRepository categoryRepository, AuthorRepository authorRepository, ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }


    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        categories = new ArrayList<>();
        authors = new ArrayList<>();
        products = new ArrayList<>();
        shoppingCarts = new ArrayList<>();


        if (userRepository.count() == 0) {
            users.add(new User("ana.naunova", "an", "Ana", "Naunova"));
            users.add(new User("ivan.danilov", "iv", "Ivan", "Danilov"));
            users.add(new User("viktorija.stamenova", "vs", "Viktorija", "Stamenova"));
            users.add(new User("admin", "admin", "Admin", "Admin"));
            userRepository.saveAll(users);
        }


        if (categoryRepository.count() == 0) {
            categories.add(new Category("Thriller", "Thriller Category"));
            categories.add(new Category("Comedy", "Comedy Category"));
            categories.add(new Category("Romance", "Romance Category"));
            categories.add(new Category("Action", "Action Category"));
            categories.add(new Category("Animation", "Animation Category"));
            categories.add(new Category("Fiction", "Fiction Category"));
            categories.add(new Category("Fantasy", "Fantasy Category"));
            categoryRepository.saveAll(categories);
        }

        if (authorRepository.count() == 0) {
            authors.add(new Author("Tessa Bailey", "USA"));
            authors.add(new Author("Kristin Hannah", "USA"));
            authors.add(new Author("Rebecca Yarros", "USA"));
            authors.add(new Author("Colleen Hoover", "USA"));
            authorRepository.saveAll(authors);
        }

        if (productRepository.count() == 0) {
            List<Category> categoryList = categoryRepository.findAll();
            List<Author> authorList = authorRepository.findAll();

            products.add(new Product("It Happened One Summer", 300d, 15, categoryList.get(2), authorList.get(0)));
            products.add(new Product("Firefly Lane", 500.99, 20, categoryList.get(5), authorList.get(1)));
            products.add(new Product(" Fourth Wing", 200d, 56, categoryList.get(6), authorList.get(2)));
            products.add(new Product("It Ends With Us", 590.99, 20, categoryList.get(2), authorList.get(3)));
            productRepository.saveAll(products);
        }


        if (shoppingCartRepository.count() == 0) {
            List<Product> productList = productRepository.findAll();
            List<User> userList = userRepository.findAll();

            ShoppingCart shoppingCart1 = new ShoppingCart(userList.get(0));
            ShoppingCart shoppingCart2 = new ShoppingCart(userList.get(1));
            ShoppingCart shoppingCart3 = new ShoppingCart(userList.get(1));

            shoppingCart1.getProducts().add(productList.get(0));

            shoppingCart2.getProducts().add(productList.get(0));
            shoppingCart2.getProducts().add(productList.get(1));
            shoppingCart2.getProducts().add(productList.get(2));

            shoppingCart3.getProducts().add(productList.get(0));

            shoppingCart1.setStatus(ShoppingCartStatus.FINISHED);
            shoppingCart2.setStatus(ShoppingCartStatus.FINISHED);
            shoppingCart3.setStatus(ShoppingCartStatus.FINISHED);

            shoppingCartRepository.save(shoppingCart1);
            shoppingCartRepository.save(shoppingCart2);
            shoppingCartRepository.save(shoppingCart3);
        }
    }

}
