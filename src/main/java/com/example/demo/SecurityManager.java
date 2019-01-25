package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Collection;

//@Configuration
@EnableWebSecurity
public class SecurityManager extends WebSecurityConfigurerAdapter {

    private Collection<GrantedAuthority> authorities;

    /*public SecurityManager() {
        authorities = (Collection<GrantedAuthority>)
          SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }*/
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    @Override
    public UserDetailsService userDetailsService () {
        UserDetails user = User.builder (). username ("user"). password (passwordEncoder(). encode ("secret")).
                roles ("USER"). build ();
        UserDetails userAdmin = User.builder (). username ("admin"). password (passwordEncoder(). encode ("secret")).
                roles ("ADMIN"). build ();

        return new InMemoryUserDetailsManager (user, userAdmin);
    }

    @Bean()
    PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/oauth/token", "/oauth/authorize **", "/publishes","/publicuri").permitAll()
                .antMatchers("/hellouser").hasAnyRole("USER","ADMIN")
                .antMatchers("/*admin*").hasAnyRole("ADMIN");
    }


    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {

        UserDetails user=User.builder().username("user").password(passwordEncoder().encode("secret")).
                roles("USER").build();
        UserDetails userAdmin=User.builder().username("admin").password(passwordEncoder().encode("secret")).
                roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,userAdmin);
    }*/

    /*@Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER").and().
                withUser("admin").password("{noop}password").roles("ADMIN");

    }
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/hello*").hasAnyRole("USER","ADMIN").and().httpBasic();
    }*/
}
