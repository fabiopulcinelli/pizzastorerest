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
@Table(name = "ruolo")
public class Ruolo {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_PIZZAIOLO = "ROLE_PIZZAIOLO";
    public static final String ROLE_PROPRIETARIO = "ROLE_PROPRIETARIO";
    public static final String ROLE_FATTORINO = "ROLE_FATTORINO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "codice")
    private String codice;
    
	public Ruolo(String descrizione, String codice) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
	}
	
	public Ruolo(Long id) {
		super();
		this.id = id;
	}
}
