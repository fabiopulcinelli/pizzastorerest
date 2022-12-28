package it.prova.pizzastorerest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pizzastorerest.model.Cliente;
import it.prova.pizzastorerest.model.Ordine;
import it.prova.pizzastorerest.model.Pizza;
import it.prova.pizzastorerest.model.Ruolo;
import it.prova.pizzastorerest.model.Utente;
import it.prova.pizzastorerest.service.ClienteService;
import it.prova.pizzastorerest.service.OrdineService;
import it.prova.pizzastorerest.service.PizzaService;
import it.prova.pizzastorerest.service.RuoloService;
import it.prova.pizzastorerest.service.UtenteService;


@SpringBootApplication
public class PizzastorerestApplication implements CommandLineRunner{
	
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private PizzaService pizzaServiceInstance;
	@Autowired
	private ClienteService clienteServiceInstance;
	@Autowired
	private OrdineService ordineServiceInstance;
	 
	
	public static void main(String[] args) {
		SpringApplication.run(PizzastorerestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//inserisco i ruoli controllando che non siano gia' nel DB
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
        }

        if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO) == null) {
            ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
        }

        if (ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO) == null) {
            ruoloServiceInstance.inserisciNuovo(new Ruolo("Proprietario", Ruolo.ROLE_PROPRIETARIO));
        }

        if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO) == null) {
            ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino", Ruolo.ROLE_FATTORINO));
        }
        
        //inserimento utenti controllando che non siano gia' nel DB
        if (utenteServiceInstance.findByUsername("admin") == null) {
            Utente admin = new Utente("admin","admin","Mario","Rossi", "admin@prova.it",LocalDate.now(),new HashSet<>(0));
            admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
            utenteServiceInstance.inserisciNuovo(admin);
            utenteServiceInstance.changeUserAbilitation(admin.getId());
        }

        if (utenteServiceInstance.findByUsername("pizzaiolo") == null) {
        	Utente pizzaiolo = new Utente("pizzaiolo","pizzaiolo","Luigi","Verdi", "pizzaiolo@prova.it",LocalDate.now(),new HashSet<>(0));
        	pizzaiolo.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
            utenteServiceInstance.inserisciNuovo(pizzaiolo);
            utenteServiceInstance.changeUserAbilitation(pizzaiolo.getId());
        }

        if (utenteServiceInstance.findByUsername("proprietario") == null) {
        	Utente proprietario = new Utente("proprietario","proprietario","Maria","Rosa", "proprietario@prova.it",LocalDate.now(),new HashSet<>(0));
        	proprietario.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO));
            utenteServiceInstance.inserisciNuovo(proprietario);
            utenteServiceInstance.changeUserAbilitation(proprietario.getId());
        }

        if (utenteServiceInstance.findByUsername("fattorino") == null) {
        	Utente fattorino = new Utente("fattorino","fattorino","Luca","Gialli", "fattorino@prova.it",LocalDate.now(),new HashSet<>(0));
        	fattorino.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO));
            utenteServiceInstance.inserisciNuovo(fattorino);
            utenteServiceInstance.changeUserAbilitation(fattorino.getId());
        }

        //inserimento pizze controllando che non siano gia' nel DB
        if(pizzaServiceInstance.findByDescrizione("Margherita") == null){
            Pizza margherita = new Pizza("Margherita","Mozzerella, Pomodoro, Basilico", 5, true);
            pizzaServiceInstance.inserisciNuovo(margherita);
        }

        if(pizzaServiceInstance.findByDescrizione("Diavola") == null){
        	Pizza diavola = new Pizza("Diavola","Pomodoro, Basilico, Ventricina", 6, true);
            pizzaServiceInstance.inserisciNuovo(diavola);
        }

        if(pizzaServiceInstance.findByDescrizione("Fiori") == null){
        	Pizza fiori = new Pizza("Fiori","Mozzarella, Fiori di zucca, Alici", 8, true);
            pizzaServiceInstance.inserisciNuovo(fiori);
        }

        //inserimento clienti controllando che non siano gia' nel DB
        if(clienteServiceInstance.findByNomeAndCognome("Fabio","Pulcinelli") == null){
            Cliente FP = new Cliente("Fabio","Pulcinelli","Viale Eretum 36",true);
            clienteServiceInstance.inserisciNuovo(FP);
        }

        if(clienteServiceInstance.findByNomeAndCognome("Nicola","Neri") == null){
        	Cliente NN = new Cliente("Nicola","Neri","Via Napli 12",true);
            clienteServiceInstance.inserisciNuovo(NN);
        }

        if(clienteServiceInstance.findByNomeAndCognome("Lucia","Marroni") == null){
        	Cliente LM = new Cliente("Lucia","Marroni","Via Mosca 52",true);
            clienteServiceInstance.inserisciNuovo(LM);
        }
        
        //creazione ordine
        if(ordineServiceInstance.findByCodice("Codice 1") == null){
        	Set<Pizza> pizzeOrdine1 = new HashSet();
        	pizzeOrdine1.add(pizzaServiceInstance.findByDescrizione("Margherita"));
        	pizzeOrdine1.add(pizzaServiceInstance.findByDescrizione("Diavola"));
        	Ordine ordine = new Ordine(LocalDate.now(), false, "Codice 1", 11, clienteServiceInstance.findByNomeAndCognome("Fabio","Pulcinelli"),
        			pizzeOrdine1, utenteServiceInstance.findByUsername("fattorino") );
        	ordineServiceInstance.inserisciNuovo(ordine);
        }
	}

}
