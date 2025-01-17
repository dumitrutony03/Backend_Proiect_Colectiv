package proiectcolectiv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class SecurityConfig {
//TODO

//    private final CustomUserDetailsService customUserDetailsService;
//
//    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/user/login").permitAll() // Public login endpoint
//                        .requestMatchers("/doctor/**").hasRole("DOCTOR") // Accessible by doctors
//                        .requestMatchers("/pacient/**").hasRole("PACIENT") // Accessible by patients
//                        .anyRequest().authenticated() // Other endpoints require authentication
//                )
//                .httpBasic(); // Use Basic Authentication (no form-based login)
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .authenticationProvider(daoAuthenticationProvider())
//                .build();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(customUserDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
}