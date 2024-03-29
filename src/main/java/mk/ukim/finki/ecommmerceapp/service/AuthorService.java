package mk.ukim.finki.ecommmerceapp.service;

import mk.ukim.finki.ecommmerceapp.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(String name, String address);
    void deleteById(Long id);

    boolean exists(Long id);

}
