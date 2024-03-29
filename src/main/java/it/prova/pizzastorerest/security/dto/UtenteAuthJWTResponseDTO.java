package it.prova.pizzastorerest.security.dto;

import java.util.List;

import lombok.Data;

@Data

public class UtenteAuthJWTResponseDTO {

    private String token;
    private String username;
    private String email;
    private List<String> roles;
    private String type = "Bearer";

    public UtenteAuthJWTResponseDTO(String accessToken, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
