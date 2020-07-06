package com.sunny.ddangnmarket.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    // Submits the KeycloakAuthenticationProvider to the AuthenticationManager
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    // Specifies the session authentication strategy
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/products", "/api/v1/products/**", "/api/v1/categories")
                .permitAll()

                .antMatchers(HttpMethod.GET, "/api/v1/products/**/comments/**")
                .permitAll()

                .antMatchers(HttpMethod.POST,  "/api/v1/products", "/api/v1/products/**/comments")
                .hasRole(ROLE_USER)

                .antMatchers(HttpMethod.PUT,  "/api/v1/products/**", "/api/v1/products/**/comments/**")
                .hasRole(ROLE_USER)

                .antMatchers(HttpMethod.DELETE, "/api/v1/products/**", "/api/v1/products/**/comments/**")
                .hasAnyRole(ROLE_USER, ROLE_ADMIN)

                .antMatchers("/users*")
                .hasAnyRole(ROLE_USER, ROLE_ADMIN)

                .anyRequest()
                .permitAll();
    }

    public static final String ROLE_USER = "user";
    public static final String ROLE_ADMIN = "admin";
}