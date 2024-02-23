package com.ecommerce.autorisation.service;

import com.ecommerce.autorisation.dto.UserDto;

import java.util.List;

public interface AccountService {

    /**
     * Méthode pour ajouter un nouveau utilisateur
     * @param user
     * @return
     */
    public UserDto save(UserDto user);

    /**
     * Méthode pour recupérer tous les utilisateurs
     * @return
     */
    public List<UserDto> findAll();

    /**
     * Méthode pour supprimer un utilisateur
     * @param id
     */
    public void delete(Integer id);

    /**
     * Ajouter une liste des utilisateurs
     * @param userDtoList
     * @return
     */
    public List<UserDto> addUsers(List<UserDto> userDtoList);

    public UserDto loadUserByEmail(String email);
}
