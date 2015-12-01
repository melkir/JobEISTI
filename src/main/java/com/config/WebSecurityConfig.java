package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // temporary disabled for testing file upload
        // Grant resource permissions
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        // Roles permissions based on directories
        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('USER')")
                .antMatchers("/ent/**").access("hasRole('ENTERPRISE')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')");
        // Roles permissions for other requests
        http.authorizeRequests()
                // All user can access
                .antMatchers("/", "/home", "/register").permitAll()
                    .anyRequest().authenticated()
                    .and()
                // Other routing need login
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                // Default logout from Spring Security
                .logout()
                    .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Add 3 login : user, admin and enterprise - cookie "JSESSIONID"
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("ent").password("ent").roles("ENTERPRISE");
    }
}