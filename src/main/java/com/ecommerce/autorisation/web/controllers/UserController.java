package com.ecommerce.autorisation.web.controllers;

import com.ecommerce.autorisation.dto.UserDto;
import com.ecommerce.autorisation.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<List<UserDto>> addUsers(@Valid @RequestBody List<UserDto> userDtoList){
        log.debug("Request pour ajouter des utilisateurs");
        var users = userService.addUsers(userDtoList);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers(){
        var usersDto = userService.findAll();
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam String id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
