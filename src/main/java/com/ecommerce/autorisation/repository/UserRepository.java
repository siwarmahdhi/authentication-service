package com.ecommerce.autorisation.repository;

import com.ecommerce.autorisation.models.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    String USERS_BY_ID_CACHE = "usersById";
    String USERS_BY_ID_AND_ACTIVE_CACHE = "usersByIdAndActive";

    String USERS_BY_EMAIL_AND_ACTIVE_CACHE = "usersByEmailAndActive";

    /**
     * Supprimer User par gid
     * @param gid grdf id
     */
    void deleteById(Integer id);

    /**
     * Récupérer user par gid
     * @param gid grdf id
     * @return user récupéré
     */
    @Cacheable(value = USERS_BY_ID_CACHE)
    Optional<User> findById(Integer id);

    /**
     * Récupérer user actif par gid
     * @param id
     * @return user récupéré
     */
    @Cacheable(value = USERS_BY_ID_AND_ACTIVE_CACHE)
    Optional<User> findByIdAndActiveTrue(Integer id);

    @Cacheable(value = USERS_BY_EMAIL_AND_ACTIVE_CACHE)
    Optional<User> findByEmailAndActiveTrue(String email);
}
