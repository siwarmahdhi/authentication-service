package com.ecommerce.autorisation.config.auth;

import com.ecommerce.autorisation.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AppAuthentication implements Authentication {


    private final User user;

    private String password;
    private Collection<? extends GrantedAuthority> grants;

    private boolean authenticated = false;

    public AppAuthentication(String email, String password) {
       this.user = User.builder().email(email).password(password).build();
    }

    public AppAuthentication(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return this.user.getEmail();
    }

    @Override
    public Object getDetails() {
        return this.user.getFirstName();
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.user.getLastName();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> grants) {
        this.grants = grants;
    }
}
