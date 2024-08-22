package com.eventregistration.system.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Map;

@Component
public class KeycloakGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakGrantedAuthoritiesConverter.class);

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess == null || !realmAccess.containsKey("roles")) {
            logger.warn("No roles found in JWT");
            return Collections.emptyList();
        }

        List<String> roles = (List<String>) realmAccess.get("roles");

        logger.info("Extracted roles from JWT: {}", roles);

        return roles.stream()
                .map(role -> {
                    String authority = "ROLE_" + role.toUpperCase();
                    logger.info("Granted Authority: {}", authority);
                    return (GrantedAuthority) () -> authority;
                })
                .collect(Collectors.toList());
    }
}

