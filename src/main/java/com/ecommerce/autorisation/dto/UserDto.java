package com.ecommerce.autorisation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

    private Integer id;
    @Size(max = 50)
    private String firstName;
    @Size(max = 50)
    private String lastName;
    private String password;
    @Email
    @Size(min = 5, max = 255)
    private String email;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    @Size(max = 8)
    private String createdBy;
    @Size(max = 8)
    private String modifiedBy;
    private Set<AuthorityDto> authorities;
}
