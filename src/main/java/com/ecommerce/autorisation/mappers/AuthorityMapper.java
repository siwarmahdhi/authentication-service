package com.ecommerce.autorisation.mappers;

import com.ecommerce.autorisation.dto.AuthorityDto;
import com.ecommerce.autorisation.models.Authority;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper extends EntityMapper<AuthorityDto, Authority> {
}
