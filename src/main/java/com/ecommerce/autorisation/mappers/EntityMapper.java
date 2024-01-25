package com.ecommerce.autorisation.mappers;

import java.util.List;

/**
 * Mapper génerique dto à entité
 *
 * @param <D> - DTO type parametre.
 * @param <E> - Entité type parametre.
 */

public interface EntityMapper<D, E> {

    /**
     * Cette méthode permet de créer un objet E à partir d'un DTO D
     *
     * @param dto
     * @return Objet D
     */
    E toEntity(D dto);

    /**
     * Cette méthode permet de créer un DTO D à partir d'un Objet E
     *
     * @param entity
     * @return Objet DTO D
     */

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
