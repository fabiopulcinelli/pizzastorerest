package it.prova.pizzastorerest.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    private Boolean closed;
    @NotBlank(message = "{codice.notblank}")
    
    private String codice;
    private Integer costo;

    private Set<PizzaDTO> pizze = new HashSet<PizzaDTO>(0);
    @NotNull(message = "{cliente.notnull}")
    private ClienteDTO cliente;
    @NotNull(message = "{fattorino.notnull}")
    private UtenteDTO fattorino;

    public OrdineDTO(Long id, LocalDate data, String codice, Boolean closed, Integer costo) {
		this.id = id;
		this.data = data;
		this.codice = codice;
		this.closed = closed;
		this.costo = costo;
	}
    
    public Ordine buildOrdineModel(){
        Ordine result = new Ordine(id,data,closed,codice,costo);

        if(pizze != null)
        result.setPizze(pizze.stream().map(PizzaDTO::buildPizzaModel).collect(Collectors.toSet()));

        if (this.fattorino != null)
			result.setFattorino(this.fattorino.buildUtenteModel(false));

		if (this.cliente != null)
			result.setCliente(this.cliente.buildClienteModel());

        return result;
    }

    public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel,boolean includePizze, boolean includeFattorino, boolean includeCliente ){
        OrdineDTO result = new OrdineDTO(ordineModel.getId(), ordineModel.getData(), ordineModel.getCodice(), ordineModel.getClosed(), ordineModel.getCostoTotale());

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
            return OrdineDTO.buildOrdineDTOFromModel(ordine, true, true, true);
        }).collect(Collectors.toList());
    }

    public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput){
        return modelListInput.stream().map(ordine -> {
            return OrdineDTO.buildOrdineDTOFromModel(ordine, true, true, true);
        }).collect(Collectors.toList());
    }
}
