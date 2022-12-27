package it.prova.pizzastorerest.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastorerest.model.Cliente;
import it.prova.pizzastorerest.model.Ordine;
import it.prova.pizzastorerest.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdineDTO {

    private Long id;

    private LocalDate data;

    private Boolean closed;
    @NotBlank(message = "{codice.notblank}")
    private String codice;
    @NotNull(message = "{costo.notnull}")
    private Integer costo;

    private Set<PizzaDTO> pizze = new HashSet<PizzaDTO>(0);
    @NotNull(message = "{cliente.notnull}")
    private ClienteDTO cliente;
    @NotNull(message = "{fattorino.notnull}")
    private UtenteDTO fattorino;

    public OrdineDTO(Long id, LocalDate data, Boolean closed, Integer costo) {
		this.id = id;
		this.data = data;
		this.closed = closed;
		this.costo = costo;
	}
    
    public Ordine buildOrdineModel(){
        Ordine result = new Ordine(id,data,closed,codice,costo);

        if(pizze != null)
        result.setPizze(pizze.stream().map(PizzaDTO::buildPizzaModel).collect(Collectors.toSet()));

        if (fattorino != null)
            result.setFattorino(new Utente(id));

        if (cliente != null)
            result.setCliente(new Cliente(id));

        return result;
    }

    public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel,boolean includePizze, boolean includeFattorino, boolean includeCliente ){
        OrdineDTO result = new OrdineDTO(ordineModel.getId(), ordineModel.getData(), ordineModel.getClosed(), ordineModel.getCostoTotale());

        if (includePizze)
            result.setPizze( PizzaDTO.createPizzaDTOSetFromModelSet(ordineModel.getPizze()));

        if(includeFattorino)
            result.setFattorino(UtenteDTO.buildUtenteDTOFromModel(ordineModel.getFattorino()));

        if(includeCliente)
            result.setCliente(ClienteDTO.buildClienteDTOFromModel(ordineModel.getCliente()));

        return result;
    }

    public static List<OrdineDTO> createOrdineDTOListFromModelSet(Set<Ordine> modelListInput){
        return modelListInput.stream().map(ordine -> {
            return OrdineDTO.buildOrdineDTOFromModel(ordine, false, false, false);
        }).collect(Collectors.toList());
    }

    public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput){
        return modelListInput.stream().map(ordine -> {
            return OrdineDTO.buildOrdineDTOFromModel(ordine, false, false, false);
        }).collect(Collectors.toList());
    }
}
