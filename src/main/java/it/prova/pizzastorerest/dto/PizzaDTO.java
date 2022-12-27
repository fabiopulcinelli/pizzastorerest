package it.prova.pizzastorerest.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastorerest.model.Pizza;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PizzaDTO {


    private Long id;
    @NotBlank(message = "{descrizione.notblank}")
    private String descrizione;
    @NotBlank(message = "{ingredienti.notnull}")
    private String ingredienti;
    @NotNull(message = "{prezzoBase.notnull}")
    private Integer prezzoBase;

    private Boolean attivo;

    public Pizza buildPizzaModel(){
        Pizza result = new Pizza(id,descrizione,ingredienti,prezzoBase,attivo);
        return result;
    }

    public static PizzaDTO buildPizzaDTOFromModel(Pizza pizzaModel) {
        return new PizzaDTO(pizzaModel.getId(),pizzaModel.getDescrizione(), pizzaModel.getIngredienti(), pizzaModel.getPrezzoBase(), pizzaModel.getAttivo());
    }

    public static List<PizzaDTO> createPizzaDTOListFromModelSet(Set<Pizza> modelListInput){
        return modelListInput.stream().map(PizzaDTO::buildPizzaDTOFromModel).collect(Collectors.toList());
    }

    public static List<PizzaDTO> createPizzaDTOListFromModelList(List<Pizza> modelListInput){
        return modelListInput.stream().map(PizzaDTO::buildPizzaDTOFromModel).collect(Collectors.toList());
    }

    public static Set<PizzaDTO> createPizzaDTOSetFromModelSet(Set<Pizza> modelListInput){
        return modelListInput.stream().map(PizzaDTO::buildPizzaDTOFromModel).collect(Collectors.toSet());
    }
}
