package it.prova.pizzastorerest.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastorerest.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {


    private Long id;

    @NotBlank(message = "{cliente.nome.notblank}")
    private String nome;

    @NotBlank(message = "{cliente.cognome.notblank}")
    private String cognome;

    @NotBlank(message = "{cliente.indirizzo.notblank}")
    private String indirizzo;

    private boolean attivo;

    public ClienteDTO(Long id, String nome, String cognome, String indirizzo, Boolean attivo) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.attivo = attivo;
	}
    
    public Cliente buildClienteModel(){
        Cliente result = new Cliente(id,nome,cognome,indirizzo,attivo);
        return result;
    }

    public static ClienteDTO buildClienteDTOFromModel(Cliente clientModel){
        ClienteDTO result = new ClienteDTO(clientModel.getId(), clientModel.getNome(), clientModel.getCognome(), clientModel.getIndirizzo(), clientModel.getAttivo());
        return result;
    }

    public static List<ClienteDTO> createClienteDTOListFromModelSet(Set<Cliente> modelListInput){
        return modelListInput.stream().map(cliente -> {
            return ClienteDTO.buildClienteDTOFromModel(cliente);
        }).collect(Collectors.toList());
    }

    public static List<ClienteDTO> createClienteDTOListFromModelList(List<Cliente> modelListInput){
        return modelListInput.stream().map(cliente -> {
            return ClienteDTO.buildClienteDTOFromModel(cliente);
        }).collect(Collectors.toList());
    }
}
