package com.ecommerce.autorisation.web.controllers;

import com.ecommerce.autorisation.config.auth.AuthoritiesConstants;
import com.ecommerce.autorisation.dto.UserDto;
import com.ecommerce.autorisation.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final AccountService accountService;

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<UserDto> addUsers(@Valid @RequestBody UserDto userDto){
        log.debug("Request pour ajouter des utilisateurs");
        var users = accountService.save(userDto);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<UserDto>> findAllUsers(){
        var usersDto = accountService.findAll();
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteUser(@RequestParam Integer id){
        accountService.delete(id);
        return ResponseEntity.ok().build();
    }
}
