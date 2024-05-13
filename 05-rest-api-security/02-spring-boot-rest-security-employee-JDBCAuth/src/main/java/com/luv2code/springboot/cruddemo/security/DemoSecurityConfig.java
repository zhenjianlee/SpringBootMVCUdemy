package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET, "api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "api/employees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "api/employees/**").hasRole("ADMIN")
        );
        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        //disable csrf
        http.csrf(csrf->csrf.disable());
        return http.build();
    }

    // Hard Coding not in use - to use JDBC security
     /*
    @Bean
    public InMemoryUserDetailsManager   userDetailsManager(){
        UserDetails jian = User.builder()
                .username("jian")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails tho = User.builder()
                .username("tho")
                .password("{noop}test123")
                .roles("MANAGER")
                .build();

        UserDetails emilia = User.builder()
                .username("emilia")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(jian,tho,emilia);
    }
    */
}
