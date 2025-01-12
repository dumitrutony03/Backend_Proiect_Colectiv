package proiectcolectiv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import proiectcolectiv.service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfigurer -> csrfConfigurer.disable()) // Disable CSRF for simplicity
                .authorizeHttpRequests(auth -> {
                    // Public endpoints (no login required)
                    auth.requestMatchers("/user/login", "/user/register").permitAll();
                    auth.requestMatchers("/hospitals/all").permitAll();

                    // Role-based access control
                    auth.requestMatchers("/appointment/**").hasRole("DOCTOR");
                    auth.requestMatchers("/pacient/**").hasRole("PACIENT");
                    auth.requestMatchers("/doctor/**").hasRole("DOCTOR");

                    // All other endpoints require authentication
                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults()); // Basic Authentication (username + password)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encrypt passwords for security
    }
}
