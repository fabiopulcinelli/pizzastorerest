package it.prova.pizzastorerest.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ordine")
public class Ordine {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "data")
    private LocalDate data;
    @Column(name = "closed")
    private Boolean closed;
    @Column(name = "codice")
    private String codice;
    @Column(name = "costoTotale")
    private Integer costoTotale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "ordine_pizza", joinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"))
    private Set<Pizza> pizze = new HashSet<Pizza>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", nullable = true)
    private Utente fattorino;

	public Ordine(LocalDate data, Boolean closed, String codice, Integer costoTotale, Cliente cliente, Set<Pizza> pizze,
			Utente fattorino) {
		super();
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.cliente = cliente;
		this.pizze = pizze;
		this.fattorino = fattorino;
	}
	
	public Ordine(Long id, LocalDate data, Boolean closed, String codice, Integer costoTotale) {
		super();
		this.id = id;
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotale = costoTotale;
	}
}
