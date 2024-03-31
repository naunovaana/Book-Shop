package mk.ukim.finki.ecommmerceapp.service.impl;

import mk.ukim.finki.ecommmerceapp.model.Category;
import mk.ukim.finki.ecommmerceapp.repository.CategoryRepository;
import mk.ukim.finki.ecommmerceapp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl  implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
//    private boolean categoryInvalid(String name) {
//        return name == null || name.isEmpty();
//    }

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Category create(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category category=new Category(name,description);
         categoryRepository.save(category);
        return category;
    }

    @Override
    public Category update(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category category=new Category(name,description);
        categoryRepository.save(category);
        return category;

    }

    @Override
    public void delete(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        categoryRepository.deleteByName(name);

    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }

    @Override
    public Optional<Category> save(String name, String description) {
        return Optional.of(this.categoryRepository.save(new Category(name, description)));
    }
}
