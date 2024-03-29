package mk.ukim.finki.ecommmerceapp.service;

import mk.ukim.finki.ecommmerceapp.model.User;
import mk.ukim.finki.ecommmerceapp.model.enumeration.Role;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    UserDetails loadUserByUsername(String username);
}
