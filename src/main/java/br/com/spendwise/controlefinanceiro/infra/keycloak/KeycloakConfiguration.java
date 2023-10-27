package br.com.spendwise.controlefinanceiro.infra.keycloak;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {

    @Value("${spring.keycloak.application.url}")
    private String url;

    @Value("${spring.keycloak.application.realm}")
    private String realm;

    @Value("${spring.keycloak.application.client}")
    private String client;

    @Value("${spring.keycloak.application.username}")
    private String username;

    @Value("${spring.keycloak.application.password}")
    private String password;

    @Bean
    Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(url)
                .realm(realm)
                .clientId(client)
                .grantType(OAuth2Constants.PASSWORD)
                .username(username)
                .password(password)
                .build();
    }
}
