
package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class CustomFilterSecurity {

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("1234")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("123")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeRequests(request -> request.requestMatchers("/product/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/signin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/main/page").permitAll()
                        .requestMatchers(HttpMethod.GET, "/index").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/login/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/login/signin").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product/shoes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product/colors").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product/sizes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product/genders").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product/materials").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product/styles").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product//categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getShoes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getSizes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getMaterials").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getStyles").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getCategories").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product/file/{filename:.+}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/signin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/main/page").permitAll()
                        .requestMatchers(HttpMethod.GET, "/index").permitAll()
                        
                        .requestMatchers(HttpMethod.GET, "/product/detail").permitAll()
                        .requestMatchers("/order/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/cart/**").permitAll()
                        .requestMatchers("/orderDetail/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/inventory/**").permitAll()
                        .anyRequest().authenticated());
                        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
