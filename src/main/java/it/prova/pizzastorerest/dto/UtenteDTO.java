package it.prova.pizzastorerest.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import it.prova.pizzastorerest.model.Ruolo;
import it.prova.pizzastorerest.model.StatoUtente;
import it.prova.pizzastorerest.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteDTO {

    private Long id;

    @NotBlank(message = "{utente.username.notblank}")
    @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
    private String username;

    @NotBlank(message = "{utente.password.notblank}")
    @Size(min = 3, max = 15, message = "Il valore inserito deve essere lungo tra {min} e {max} caratteri")
    private String password;

    private String confermaPassword;

    @NotBlank(message = "{utente.nome.notblank}")
    private String nome;

    @NotBlank(message = "{utente.cognome.notblank}")
    private String cognome;

    @NotBlank(message = "{utente.email.notblank}")
    private String email;

    private LocalDate dateCreated;

    private StatoUtente stato;

    private Long[] ruoliIds;

    public UtenteDTO(Long id, String username, String nome, String cognome, StatoUtente stato) {
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.stato = stato;
	}
    
    public Utente buildUtenteModel(boolean includeIdRoles) {
        Utente result = new Utente(id,username,password,nome,cognome,email,dateCreated);
        result.setStato(stato);

        if (includeIdRoles && ruoliIds != null)
            result.setRuoli(Arrays.asList(ruoliIds).stream().map(id -> new Ruolo(id) ).collect(Collectors.toSet()));

        return result;
    }

    public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {
        UtenteDTO result = new UtenteDTO(utenteModel.getId(), utenteModel.getUsername(), utenteModel.getNome(), utenteModel.getCognome(),
        		utenteModel.getStato());

        if (!utenteModel.getRuoli().isEmpty())
            result.ruoliIds = utenteModel.getRuoli().stream().map(r -> r.getId()).collect(Collectors.toList())
                    .toArray(new Long[] {});

        return result;
    }
}
