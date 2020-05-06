package com.gomokumanager.GomokuManager.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Glues authentication with the main application API
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // We use JWT so no sesion needed
            .and()
            .addFilter(new JwtAuthentificationFilter(authenticationManager()))
            .antMatchers("/api/games").hasRole("ROLE_ADMINISTRATOR")
            .antMatchers("/api/players").hasRole("ROLE_ADMINISTRATOR");
    }
}
