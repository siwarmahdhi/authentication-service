package com.ecommerce.autorisation.config.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.autorisation.exceptions.LoginAuthenticationException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!hasAuthorisation(request)) {
            log.warn("Attention aucun login et mot de passe n'a été positionné sur l'Authorization");
            throw new LoginAuthenticationException("Attention aucun login et mot de passe n'a été positionné sur l'Authorization");
        }

        byte[] decode = Base64.getDecoder().decode(request.getHeader("Authorization")
            .substring(6));
        String[] authentication = new String(decode).split(":");
        if (!hasLoginAndPassword(authentication)) {
            log.warn("Unauthorized: Full authentication is required to access");
            throw new LoginAuthenticationException("Unauthorized: Full authentication is required to access");
        }
        String email = authentication[0];
        String password = authentication[1];
        var auth = new AppAuthentication(email, password);
        auth.setAuthenticated(true);

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getCredentials(), auth.getPassword()));
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();

        var auth = new AppAuthentication(user.getUsername(), user.getPassword());
        List<String> roles=new ArrayList<>();
        authResult.getAuthorities().forEach(a->{
            roles.add(a.getAuthority());
        });

        String jwt= JWT.create()
            .withIssuer(request.getRequestURI())
            .withSubject((String) auth.getCredentials())
            .withArrayClaim("roles",roles.toArray(new String[roles.size()]))
            .withExpiresAt(new Date(System.currentTimeMillis() + 300000))
            .sign(Algorithm.HMAC256("secretKeyAlgo"));

        response.addHeader("Authorization",jwt);
    }

    /**
     * Vérifier si la requête http contient le login et password
     *
     * @param request requet http
     * @return true si requete valide et false sinon
     */
    private boolean hasAuthorisation(HttpServletRequest request) {
        return StringUtils.hasText(request.getHeader("Authorization"));
    }

    private boolean hasLoginAndPassword(String[] authentication) {
        return authentication.length > 1 && authentication[0] != null
            && authentication[1] != null;
    }
}
