package com.ecommerce.autorisation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document(collection = "Utilisateur")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class User extends AbstractModel implements Serializable {

    @Id
    private String id;
    /**
     * Prenom
     */
    @NotNull
    private String firstName;
    /**
     * nom
     */
    @NotNull
    private String lastName;
    /**
     * Mot de passe
     */
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * Email
     */
    @Email
    @NotNull
    private String email;
}
