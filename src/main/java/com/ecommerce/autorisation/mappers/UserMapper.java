package com.ecommerce.autorisation.mappers;

import com.ecommerce.autorisation.dto.UserDto;
import com.ecommerce.autorisation.models.Status;
import com.ecommerce.autorisation.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

    @Named("isActive")
    default boolean isActive(Status status) {
        return status.equals(Status.ACTIVE);
    }

    @Mapping(ignore = true, target = "password")
    UserDto toDto(User user);
}
