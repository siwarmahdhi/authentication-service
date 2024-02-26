package com.ecommerce.autorisation.service;

import com.ecommerce.autorisation.dto.AuthorityDto;
import com.ecommerce.autorisation.dto.UserDto;
import com.ecommerce.autorisation.models.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    /**
     * Méthode pour ajouter un nouveau utilisateur
     * @param user
     * @return
     */
     UserDto save(UserDto user);

    /**
     * Méthode pour recupérer tous les utilisateurs
     * @return
     */
     List<UserDto> findAll();

    /**
     * Méthode pour supprimer un utilisateur
     * @param id
     */
     void delete(Integer id);

    /**
     * Ajouter une liste des utilisateurs
     * @param userDtoList
     * @return
     */
     List<UserDto> addUsers(List<UserDto> userDtoList);

     Optional<User> loadUserByEmail(String email);

     UserDto addRoleToUser(String roleName, String email);
}
