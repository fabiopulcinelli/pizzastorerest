package it.prova.pizzastorerest.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticheVarieOrdiniDTO {
	@NotNull(message = "{data.notnull}")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataInizio;
	
	@NotNull(message = "{data.notnull}")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataFine;
}
