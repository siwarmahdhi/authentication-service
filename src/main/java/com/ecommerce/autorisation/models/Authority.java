package com.ecommerce.autorisation.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@NotNull
@Entity
@Table(name = "authority")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Authority implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name=  "ROLE_ID",unique  = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    @SequenceGenerator(name = "role_id_seq", allocationSize = 1)
    private Integer roleId;

    @Size(max = 50)
    @Column(length = 50)
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}
