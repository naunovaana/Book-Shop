package mk.ukim.finki.ecommmerceapp.repository;

import mk.ukim.finki.ecommmerceapp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
