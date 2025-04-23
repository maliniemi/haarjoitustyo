package com.example.harjoitustyo.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 1) Päänäkymä (/): pääsy kaikille kirjautuneille käyttäjille
                        .requestMatchers("/").authenticated()
                        // 2) User-sivut: vain USER- ja ADMIN-rooleille
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        // 3) ADMIN-reitit: vain ADMIN-roolille
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // 4) Sallitaan kaikki Vaadin-resurssit ja yleiset reitit
                        .requestMatchers(
                                "/login", "/login/**", "/logout",
                                "/frontend/**", "/VAADIN/**",
                                "/webjars/**", "/icons/**", "/images/**",
                                "/styles/**", "/manifest.webmanifest",
                                "/sw.js", "/offline-page.html"
                        ).permitAll()
                        // 5) Kaikki muut reitit vaativat kirjautumisen
                        .anyRequest().authenticated()
                )
                // 6) Poistetaan CSRF tietyiltä Vaadin-endpointeilta
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/VAADIN/**"),
                                new AntPathRequestMatcher("/frontend/**")
                        )
                );
        setLoginView(http, "/login");
    }
}
