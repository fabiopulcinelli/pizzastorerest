package it.prova.pizzastorerest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "pizza")
public class Pizza {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "ingredienti")
    private String ingredienti;
    @Column(name = "prezzoBase")
    private Integer prezzoBase;
    @Column(name = "attivo")
    private Boolean attivo;
    
	public Pizza(String descrizione, String ingredienti, Integer prezzoBase, Boolean attivo) {
		super();
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
		this.prezzoBase = prezzoBase;
		this.attivo = attivo;
	}
}
