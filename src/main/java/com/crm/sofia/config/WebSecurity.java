package com.crm.sofia.config;

import com.crm.sofia.filters.JWTAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.List;

//@Configuration
//@EnableWebSecurity
//public class WebSecurity extends WebSecurityConfigurerAdapter {
//
//
//
//      private final JWTAuthFilter jwtAuthFilter;
//
//  public WebSecurity(JWTAuthFilter jwtAuthFilter) {
//    this.jwtAuthFilter = jwtAuthFilter;
//  }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
//                .authorizeRequests().antMatchers("*").permitAll();
//    }


//      @Override
//  protected void configure(HttpSecurity http) throws Exception {
//          Filter jwtAuthFilter;
//          http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
//        .authorizeRequests()
//        //        .antMatchers("/v2/api-docs",
//        //            "/configuration/ui",
//        //            "/swagger-resources/**",
//        //            "/configuration/security",
//        //            "/swagger-ui.html",
//        //            "/webjars/**").permitAll()
//        .antMatchers("/users/auth").permitAll()
//                  .antMatchers("*").permitAll()
////        .antMatchers("/users/**").hasAnyAuthority("admin", "user")
////        .antMatchers(HttpMethod.PUT, "/users/user-detail").hasAuthority("admin")
////        .antMatchers(HttpMethod.PUT, "/users/user-password").hasAuthority("admin")
////        .antMatchers("/user-groups/**").hasAnyAuthority("admin", "user")
////        .antMatchers("/contacts/**").hasAnyAuthority("admin", "user")
////        .antMatchers("/notifications/**").hasAnyAuthority("admin", "user")
////        .antMatchers("/countries/**").hasAnyAuthority("admin", "user")
////        .antMatchers("/encryption/aes-keys/**").hasAnyAuthority("admin", "user")
////        .antMatchers(HttpMethod.GET, "/encryption/aes-keys").hasAnyAuthority("admin")
////        .antMatchers(HttpMethod.GET, "/encryption/aes-keys/pages").hasAnyAuthority("admin")
////        .antMatchers("/encryption/rsa-keys/**").hasAnyAuthority("admin", "user")
////        .antMatchers("/message-infos/**").hasAnyAuthority("admin", "user")
////        .antMatchers(HttpMethod.GET, "/modules/market/products/").hasAuthority("admin")
////        .antMatchers(HttpMethod.GET, "/modules/market/products/market-transactions/")
////        .hasAuthority("admin")
////        .antMatchers("/modules/market/**").hasAuthority("market")
////        .antMatchers("/modules/hyppo-io/**").hasAuthority("hyppoio")
////        .antMatchers("/modules/file-uploader/**").hasAnyAuthority("admin", "user")
//
//        .anyRequest().authenticated()
//        .and()
//        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//    // .antMatchers("*").permitAll();
//  }
//
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
