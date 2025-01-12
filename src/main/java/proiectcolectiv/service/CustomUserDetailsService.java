package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.UserData;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService; // Reuse your existing service

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData user = userService.findByUserName(username); // Find user in MongoDB
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Convert your user data into Spring Security's UserDetails
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword()) // Encrypted password
                .roles(user.getRole()) // Role (e.g., DOCTOR or PACIENT)
                .build();
    }
}
