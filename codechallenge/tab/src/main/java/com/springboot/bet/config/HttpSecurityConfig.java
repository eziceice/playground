package com.springboot.bet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Http Basic Security Config
 */
@Configuration
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> userConfig =
                auth.inMemoryAuthentication().passwordEncoder(passwordEncoder);

        userConfig.withUser("admin").password(passwordEncoder.encode("12345")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //HTTP Basic authentication
        httpSecurity.httpBasic()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
