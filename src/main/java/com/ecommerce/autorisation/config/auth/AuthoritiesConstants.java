package com.ecommerce.autorisation.config.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constantes Spring Security authorities.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
}
