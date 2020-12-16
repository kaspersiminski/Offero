package com.simek.offero.infrastructure.security;

import com.simek.offero.core.account.ports.incoming.SpringSecurityUserFindableByEmail;
import com.simek.offero.infrastructure.security.properties.JWTProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final JWTProperties jwtProperties;
    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public SecurityConfig(JWTProperties JWTProperties, BCryptPasswordEncoder bCryptPasswordEncoder, SpringSecurityUserFindableByEmail springSecurityUserFindableByEmail) {
        this.jwtProperties = JWTProperties;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = new UserDetailsServiceImpl(springSecurityUserFindableByEmail);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/account/**","/user/login/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                .and()
                    .addFilter(jwtAuthenticationFilter())
                    .addFilter(jwtAuthorizationFilter())
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .csrf()
                        .disable();


    }

    private JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        final JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.setSecret(jwtProperties.getSecret());
        jwtAuthenticationFilter.setExpTime(jwtProperties.getExpTime());
        jwtAuthenticationFilter.setFilterProcessesUrl("/user/login");
        return jwtAuthenticationFilter;
    }

    private JWTAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        final JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(authenticationManager());
        jwtAuthorizationFilter.setAuthHeaderName(jwtProperties.getAuthHeaderName());
        jwtAuthorizationFilter.setTokenPrefix(jwtProperties.getTokenPrefix());
        jwtAuthorizationFilter.setSecret(jwtProperties.getSecret());
        return jwtAuthorizationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }
}
