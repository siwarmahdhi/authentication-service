package com.ecommerce.autorisation.repository;

import com.ecommerce.autorisation.models.Authority;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository de l'entité Authority.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    String ALL_AUTHORITIES = "allAuthorities";
    /**
     * Récupérer liste de tous les authorisations
     * @return la liste des authorisations.
     */
    @Cacheable(value = ALL_AUTHORITIES)
    List<Authority> findAll();

    Optional<Authority> findByName(String name);
}
