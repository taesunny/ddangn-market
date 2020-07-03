package com.sunny.ddangnmarket.util;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;

import javax.servlet.http.HttpServletRequest;

public class KeyCloakUtils {
    public static String getUserId(HttpServletRequest request) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
//        username = accessToken.getPreferredUsername();
        return accessToken.getSubject();
//        lastname = accessToken.getFamilyName();
//        firstname = accessToken.getGivenName();
//        realmName = accessToken.getIssuer();
//        AccessToken.Access realmAccess = accessToken.getRealmAccess();
//        roles = realmAccess.getRoles();
    }

    public static String getUserEmail(HttpServletRequest request) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
//        username = accessToken.getPreferredUsername();
        return accessToken.getEmail();
//        lastname = accessToken.getFamilyName();
//        firstname = accessToken.getGivenName();
//        realmName = accessToken.getIssuer();
//        AccessToken.Access realmAccess = accessToken.getRealmAccess();
//        roles = realmAccess.getRoles();
    }
}
