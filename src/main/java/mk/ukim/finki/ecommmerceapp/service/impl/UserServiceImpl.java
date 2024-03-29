package mk.ukim.finki.ecommmerceapp.service.impl;

import mk.ukim.finki.ecommmerceapp.model.User;
import mk.ukim.finki.ecommmerceapp.model.enumeration.Role;
import mk.ukim.finki.ecommmerceapp.repository.UserRepository;
import mk.ukim.finki.ecommmerceapp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException();
        }

        if(this.userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException(username);
        }

        User user = new User(
                username,
                passwordEncoder.encode(password),
                name,
                surname,
                role
        );

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }



}
