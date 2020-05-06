package com.gomokumanager.GomokuManager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gomokumanager.GomokuManager.models.LoginView;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Contains the authentification filter implemented with JWT
 */

public class JwtAuthentificationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthentificationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Triggers when we make a post for login
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //Grab credentials and map them into loginview model
        LoginView credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginView.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create the login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                new ArrayList<>());
        )

        //Authenticate user
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        return auth;
    }

    /**
     * Treats the case when the authentication was successful
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //Grab principal
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

        //Create JWT tokens
        String token = JWT.create()
                .withSubject(principal.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JwtProperties.SECRET.getBytes()));

        // Add tokens in response
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
    }
}
