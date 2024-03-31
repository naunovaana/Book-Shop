package mk.ukim.finki.ecommmerceapp.service.impl;

import mk.ukim.finki.ecommmerceapp.model.Author;
import mk.ukim.finki.ecommmerceapp.repository.AuthorRepository;
import mk.ukim.finki.ecommmerceapp.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String address) {
        Author manufacturer=new Author(name,address);
        return Optional.of(this.authorRepository.save(manufacturer));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);

    }

//    @Override
//    public boolean exists(Long id) {
//        return this.authorRepository.existsById(id);
//    }
}
