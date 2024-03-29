package mk.ukim.finki.ecommmerceapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticator customAuthenticator;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomAuthenticator customAuthenticator) {
        this.passwordEncoder = passwordEncoder;
        this.customAuthenticator = customAuthenticator;
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests( (requests) -> requests
//                        .requestMatchers("/", "/home", "/assets/**", "/register")
//                        .permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest()
//                        .authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                        .failureUrl("/login?error=BadCredentials")
//                        .defaultSuccessUrl("/products", true)
//                )
//                .logout((logout) -> logout
//                        .logoutUrl("/logout")
//                        .clearAuthentication(true)
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .logoutSuccessUrl("/login")
//                )
//                .exceptionHandling((ex) -> ex
//                        .accessDeniedPage("/access_denied")
//                );
//
//        return http.build();
//    }

    //In memory authentication
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("ana.naunova")
                .password(passwordEncoder.encode("an"))
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("ivan.danilov")
                .password(passwordEncoder.encode("iv"))
                .roles("USER")
                .build();
        UserDetails user3 = User.builder()
                .username("viktorija.stamenova")
                .password(passwordEncoder.encode("vi"))
                .roles("USER")
                .build();
        UserDetails user4 = User.builder()
                .username("daniela.naunova")
                .password(passwordEncoder.encode("dn"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3, user4, admin);
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticator);
        return authenticationManagerBuilder.build();
    }
}
