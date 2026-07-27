package com.bookit.bookit.security;


import com.bookit.bookit.auth.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Endpoints sichern
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // ermöglicht uns, bis auf die Methode spezifisch festzulegen, wer was darf.
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Cors konfigurieren, damit unsere Cors-Config benutzt wird.
        http
                .cors(Customizer.withDefaults())
                // csrf disablen -> eigentlich schlecht aber mit csrf wird unser Backend zu komplex
                .csrf(AbstractHttpConfigurer::disable)
                // RESTful (state-less)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // URLs welche alle User aufrufen dürfen auch ohne Token
                        .requestMatchers("/auth/**").permitAll() // Hier alle Methoden
                        .requestMatchers(HttpMethod.GET, "/users/**").permitAll()
                        // Alles andere braucht ein Token
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                // Prüfung: Gibts im Header ein Authorization-String?
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
