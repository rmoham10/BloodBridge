package org.example.bloodbridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for APIs; enable it for forms in production
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html", "/admin/login", "/admin-login.html", "/admin-dashboard.html", "/register", "/registration.html").permitAll() // Public pages
                .requestMatchers("/api/users/**").permitAll()
                .requestMatchers("/api/admin/**").permitAll()
                .anyRequest().authenticated() // Secure all other routes
            )
            .formLogin(form -> form
                .loginPage("/admin/login") // Custom admin login page
                //.defaultSuccessUrl("/admin/dashboard", true) // Redirect after admin login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") // Redirect to index page after logout
                .permitAll()
            );

        return http.build();
    }
}
