
package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
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
        http
                .authorizeRequests(request -> request.requestMatchers("/product/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/signin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/main/page").permitAll()
                        .requestMatchers(HttpMethod.GET, "/index").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/login/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/login/signin").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin").permitAll()
                        // Shoes
                        .requestMatchers(HttpMethod.POST, "/admin/product/addShoes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getShoes").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/product/updateShoesName").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/product/updateShoesPrice").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/checkShoesNameExists").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getShoesNames").permitAll()
                        // Colors
                        .requestMatchers(HttpMethod.POST, "/admin/product/addColors").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getColors").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/product/updateColorName").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/product/updateColorCode").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/checkColorNameExists").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/checkColorCodeExists").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getColorsNames").permitAll()
                        // Sizes
                        .requestMatchers(HttpMethod.POST, "/admin/product/addSizes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getSizes").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/product/updateSizeName").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/checkSizeExists").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getSizesNames").permitAll()
                        // Styles
                        .requestMatchers(HttpMethod.POST, "/admin/product/addStyles").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getStyles").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/product/updateStyleName").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/checkStyleExists").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getStylesNames").permitAll()

                        // Materials
                        .requestMatchers(HttpMethod.POST, "/admin/product/addMaterials").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getMaterials").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/product/updateMaterialName").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/checkMaterialExists").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getMaterialsNames").permitAll()
                        // Categories
                        .requestMatchers(HttpMethod.POST, "/admin/product/addCategories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getCategories").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/product/updateCategoryName").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/checkCategoryExists").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/product/getCategoriesNames").permitAll()

                        // Inventory
                        .requestMatchers(HttpMethod.GET, "/admin/product/getInventory").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product/addInventory").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/product/updateInventory/**").permitAll()

                        // Product
                        .requestMatchers(HttpMethod.GET, "/admin/product/getProduct").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product/addProduct").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/product/updateProduct/**").permitAll()


                        //Orders
                        .requestMatchers(HttpMethod.GET, "/admin/product/getOrders").permitAll()

                        .requestMatchers(HttpMethod.POST, "/admin/product/genders").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/product/file/{filename:.+}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/signin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/main/page").permitAll()
                        .requestMatchers(HttpMethod.GET, "/index").permitAll()

                        // .requestMatchers(HttpMethod.GET, "/product/detail").permitAll()
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
