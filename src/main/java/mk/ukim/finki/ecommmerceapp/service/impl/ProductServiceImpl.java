package mk.ukim.finki.ecommmerceapp.service.impl;

import mk.ukim.finki.ecommmerceapp.model.Category;
import mk.ukim.finki.ecommmerceapp.model.Author;
import mk.ukim.finki.ecommmerceapp.model.Product;
import mk.ukim.finki.ecommmerceapp.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.ecommmerceapp.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.ecommmerceapp.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.ecommmerceapp.repository.CategoryRepository;
import mk.ukim.finki.ecommmerceapp.repository.AuthorRepository;
import mk.ukim.finki.ecommmerceapp.repository.ProductRepository;
import mk.ukim.finki.ecommmerceapp.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long authorId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException(categoryId));
        Author author=this.authorRepository.findById(authorId).orElseThrow(()->new ManufacturerNotFoundException(authorId));
        return Optional.of(this.productRepository.save(new Product(name, price, quantity, category, author)));
    }

    @Override
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long authorId) {
        Product product=productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException(categoryId));
        Author author=this.authorRepository.findById(authorId).orElseThrow(()->new ManufacturerNotFoundException(authorId));
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setAuthor(author);
        product.setCategory(category);
        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);

    }
}
