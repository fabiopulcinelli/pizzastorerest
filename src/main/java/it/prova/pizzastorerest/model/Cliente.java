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
@Table(name = "cliente")
public class Cliente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "indirizzo")
    private String indirizzo;
    @Column(name = "attivo")
    private Boolean attivo;
    
	public Cliente(String nome, String cognome, String indirizzo, Boolean attivo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.attivo = attivo;
	}
}
