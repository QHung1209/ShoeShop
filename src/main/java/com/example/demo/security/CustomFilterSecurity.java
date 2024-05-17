
package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class CustomFilterSecurity {

    @Autowired
    CustomJwtFilter jwtFilter;

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
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/user/**").permitAll()
//                        .requestMatchers("/admin/**").permitAll()
//                        .requestMatchers("/product/**").permitAll()
//                        .requestMatchers("/cart/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
//                        .requestMatchers("/index/**").permitAll()
//                        .requestMatchers("/main/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//        http.authorizeRequests(request -> request
//        .requestMatchers(HttpMethod.GET, "/product/allproduct").permitAll()
//        .requestMatchers(HttpMethod.GET, "/product/allstylename").permitAll()
//        .requestMatchers(HttpMethod.GET, "/product/allcategoryname").permitAll()
//        .requestMatchers(HttpMethod.GET, "/product/allmaterialname").permitAll()
//        .requestMatchers(HttpMethod.POST, "/login/signin").permitAll()
//        .requestMatchers(HttpMethod.POST, "/main/page").permitAll()
//        .requestMatchers(HttpMethod.GET, "/index").permitAll()
//        .requestMatchers(HttpMethod.POST, "/admin/login/signup").permitAll()
//        .requestMatchers(HttpMethod.POST, "/admin/login/signin").permitAll()
//        .requestMatchers(HttpMethod.GET, "/admin").permitAll()
//        .requestMatchers(HttpMethod.POST, "/admin/product/shoes").permitAll()
//        .requestMatchers(HttpMethod.POST, "/admin/product/colors").permitAll()
//        .requestMatchers(HttpMethod.POST, "/admin/product/sizes").permitAll()
//        .requestMatchers(HttpMethod.POST, "/admin/product/genders").permitAll()
//        .requestMatchers(HttpMethod.POST, "/admin/product/materials").permitAll()
//        .requestMatchers(HttpMethod.POST, "/admin/product/styles").permitAll()
//        .requestMatchers(HttpMethod.POST, "/admin/product//categories").permitAll()
//        .requestMatchers(HttpMethod.GET, "/admin/product/getShoes").permitAll()
//        .requestMatchers(HttpMethod.GET, "/admin/product/getSizes").permitAll()
//        .requestMatchers(HttpMethod.GET, "/admin/product/getMaterials").permitAll()
//        .requestMatchers(HttpMethod.GET, "/admin/product/getStyles").permitAll()
//        .requestMatchers(HttpMethod.GET, "/admin/product/getCategories").permitAll()
//        .requestMatchers(HttpMethod.POST, "/admin/product/file/{filename:.+}").permitAll()
//        .requestMatchers(HttpMethod.GET, "/product/allproduct").permitAll()
//        .requestMatchers(HttpMethod.GET, "/product/allstylename").permitAll()
//        .requestMatchers(HttpMethod.GET, "/product/allcategoryname").permitAll()
//        .requestMatchers(HttpMethod.GET, "/product/allmaterialname").permitAll()
//        .requestMatchers(HttpMethod.GET, "/product/filter").permitAll()
//        .requestMatchers(HttpMethod.POST, "/login/signin").permitAll()
//        .requestMatchers(HttpMethod.POST, "/main/page").permitAll()
//        .requestMatchers(HttpMethod.GET, "/index").permitAll()
//        .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
//        .requestMatchers(HttpMethod.POST, "/cart/**").permitAll()
//        .requestMatchers(HttpMethod.GET, "/cart/**").permitAll()
//        .requestMatchers(HttpMethod.GET, "/product/detail").permitAll()
//        .anyRequest().authenticated());


        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
