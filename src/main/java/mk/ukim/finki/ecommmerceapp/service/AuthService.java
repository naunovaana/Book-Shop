package mk.ukim.finki.ecommmerceapp.service;

import mk.ukim.finki.ecommmerceapp.model.User;

public interface AuthService {
    User login(String username,String password);
    User register(String username,String password,String repeatPassword,String name,String surname);

}
