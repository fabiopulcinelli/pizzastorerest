package it.prova.pizzastorerest.security.dto;

import java.util.List;

import lombok.Data;

@Data

public class UtenteInfoJWTResponseDTO {

    private String nome;
    private String cognome;
    private String username;
    private String email;
    private List<String> roles;
    private String type = "Bearer";

    public UtenteInfoJWTResponseDTO(String nome, String cognome, String username, String email, List<String> roles) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
