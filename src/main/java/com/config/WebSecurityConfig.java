package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomSuccessHandler customSuccessHandler;

	/*    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // In memory login - cookie "JSESSIONID"
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("ent").password("ent").roles("ENTERPRISE");
    }
    */

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
        // login with hibernate and encode password
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // temporary disabled for testing file upload
        http.headers().frameOptions().disable(); // disabled for h2 console
        // Grant resource permissions
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        // Roles permissions
        http.authorizeRequests()
                // All user can access
                .antMatchers("/", "/home", "/register").permitAll()
                // Directories access permissions
                .antMatchers("/user/**").access("hasRole('USER')")
                .antMatchers("/ent/**").access("hasRole('ENTERPRISE')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/db/**").access("hasRole('DBA')")
                // Other requests need the login
                .anyRequest().authenticated()
                // Configure the login page
                .and().formLogin().loginPage("/login").successHandler(customSuccessHandler).permitAll()
                // Default logout from Spring Security
                .and().logout().permitAll();
    }

}