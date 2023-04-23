package com.adminmodule.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
        jsr250Enabled = true,
        securedEnabled = true
)
public class SecurityConfiguration {

    private final AccountDetailsService userService;
    private final JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.setAllowedOrigins(List.of("*"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Autowired
    public SecurityConfiguration(JwtFilter jwtFilter, AccountDetailsService userService) {
        this.jwtFilter = jwtFilter;
        this.userService = userService;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/v1/auth").permitAll()
                .antMatchers( "api/v1/vendor/**/vendor-admin").permitAll()
                .antMatchers("/api/v1/employee/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/api/v1/vendor-admin/all").hasRole("ADMIN")
//                .antMatchers( "/api/v1/vendor-admin/**").hasAnyRole("VENDOR_ADMIN", "ADMIN")
                .antMatchers("/api/v1/vendor").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/v1/vendor/all").hasRole("ADMIN")
//                .antMatchers("/api/v1/vendor").hasAnyRole("VENDOR_ADMIN", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/v1/customer").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/customer/all").hasRole("ADMIN")
                .antMatchers("/api/v1/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}