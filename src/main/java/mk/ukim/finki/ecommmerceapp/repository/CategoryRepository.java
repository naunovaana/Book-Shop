package mk.ukim.finki.ecommmerceapp.repository;

import mk.ukim.finki.ecommmerceapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAllByNameLike(String text);
    void deleteByName(String name);


}
