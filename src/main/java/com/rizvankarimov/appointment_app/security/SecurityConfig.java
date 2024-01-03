package com.rizvankarimov.appointment_app.security;

import io.micrometer.common.util.internal.logging.InternalLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImplement userDetailsServiceImplement;
    private PasswordEncoder passwordEncoder;
    private InternalLogger logger;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/error", "/api/user/**").permitAll();
                    auth.requestMatchers("/api/admin/**").hasRole("ADMIN").anyRequest().fullyAuthenticated();
                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout" , "POST")))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Override                                                              // this is for configuring the authentication manager builder
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsServiceImplement).passwordEncoder(passwordEncoder);
    }

    @Bean                                                                   // this is for configuring the web mvc configurer
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
            }
        };
    }
}
