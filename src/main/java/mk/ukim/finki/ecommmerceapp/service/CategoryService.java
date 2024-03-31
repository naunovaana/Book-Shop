package mk.ukim.finki.ecommmerceapp.service;

import mk.ukim.finki.ecommmerceapp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> listCategories();
    Optional<Category> findById(Long id);
    Category create(String name,String description);
    Category update(String name,String description);
    void delete(String name);
    List<Category> searchCategories(String searchText);
    Optional<Category> save(String name,String description);
}
