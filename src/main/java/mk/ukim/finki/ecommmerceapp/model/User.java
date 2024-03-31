package mk.ukim.finki.ecommmerceapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "shop_users")
public class User  {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    private String password;

    private String name;
    private String surname;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;
//    @Enumerated(value = EnumType.STRING)
//    private Role role;



    public User(String username, String password, String name,String surname) {
        this.username = username;
        this.password = password;
//        this.role = role;
        this.name=name;
        this.surname=surname;
    }

}
