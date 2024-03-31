package mk.ukim.finki.ecommmerceapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "author_address")
    private String address;


    public Author(String name, String address) {
        this.name = name;
        this.address= address;
    }


}
