package com.example.demo;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/oauth/token", "/oauth/authorize **", "/publishes","/publicuri").permitAll()
                .antMatchers("/hellouser").hasAnyRole("USER","ADMIN")
                .antMatchers("/*admin*").hasAnyRole("ADMIN");
    }
}
