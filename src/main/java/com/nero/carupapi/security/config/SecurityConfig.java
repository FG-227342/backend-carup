package com.nero.carupapi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
    @Configuration
    public class SecurityConfig {
        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTValidationFilter jwtValidationFilter) throws Exception {

     //     var requestHandler = new CsrfTokenRequestAttributeHandler();
       //     requestHandler.setCsrfRequestAttributeName("_csrf");

            http.sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.authorizeHttpRequests(auth-> auth.requestMatchers("/api/authenticate").permitAll());
            http.authorizeHttpRequests(auth->auth.anyRequest().authenticated()).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());



            http.cors(cors-> corsConfigurationSource());

            http.csrf(csrf->csrf.disable());


             http.addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class);

            return http.build();
      }

    // quitar esto despues/*
     @Bean
        PasswordEncoder passwordEncoder(){
            return NoOpPasswordEncoder.getInstance();
        }

            @Bean
            AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
            }

        @Bean
        CorsConfigurationSource corsConfigurationSource(){
            var config = new CorsConfiguration();
            // config.setAllowedOrigins(List.of("http://localhost:3000"));
            config.setAllowedOrigins(List.of("*"));
            // config.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
            config.setAllowedMethods(List.of("*"));
            config.setAllowedHeaders(List.of("*"));

            var source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config);
            return source;
        }



    }
