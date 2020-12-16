package com.simek.offero.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Getter
@Setter
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private String authHeaderName;
    private String tokenPrefix;
    private String secret;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(authHeaderName);

        if(header == null || !header.startsWith(tokenPrefix)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = processHeader(header);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken processHeader(String token) {
        String user = JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token.replace(tokenPrefix, ""))
                .getSubject();

        if(user == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }
}
