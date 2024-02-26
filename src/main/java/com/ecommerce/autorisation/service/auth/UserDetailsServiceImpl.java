package com.ecommerce.autorisation.service.auth;

import com.ecommerce.autorisation.mappers.UserMapper;
import com.ecommerce.autorisation.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var existingUser = accountService.loadUserByEmail(email);
        if(existingUser.isPresent()){
            log.info("User exist in the database {}", existingUser.get());
            return new User(existingUser.get().getEmail(),existingUser.get().getPassword(), existingUser.get().getAuthorities());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
