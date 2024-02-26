package com.ecommerce.autorisation.web.controllers;

import com.ecommerce.autorisation.config.auth.AuthoritiesConstants;
import com.ecommerce.autorisation.dto.AuthorityDto;
import com.ecommerce.autorisation.dto.UserDto;
import com.ecommerce.autorisation.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/roles")
@Slf4j
@AllArgsConstructor
public class AuthorityController {

    private final AccountService accountService;

    @PostMapping("/addRoleToUser")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<UserDto> addRoleToUser(@RequestParam(name = "role_name") String roleName,
                                            @RequestParam(name = "email") String email){
        log.debug("Request pour ajouter un role à un utilisateur");
        var users = accountService.addRoleToUser(roleName, email);
        return ResponseEntity.ok().body(users);
    }
}
