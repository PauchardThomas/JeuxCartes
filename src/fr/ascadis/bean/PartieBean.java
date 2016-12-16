package fr.ascadis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.ascadis.dao.PartieDAO;
import fr.ascadis.dao.UtilisateurDAO;
import fr.ascadis.model.Partie;
import fr.ascadis.model.Utilisateur;

/**
 * Bean d'une partie
 * @author tpauchard
 *
 */
@Component
@Scope(value="request")
public class PartieBean implements Serializable {

	/**
	 * Numéro de serialisation du bean 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Injection de d�pendences de la gestion des donn�es d'une partie
	 */
	@Autowired
	private PartieDAO partieDAO;
	
    /**
     * Récupère la liste des parties à rejoindre
     * @return List<Partie liste de partie � rejoindre
     */
    public List<Partie> partieARejoindre() {
        
        return this.partieDAO.getPartieARejoindre();
        
    }
    
    /**
     * Récupère une partie
     * @param idPartie id de la partie à récupérer
     * @return Partie partie réupérée
     */
    public Partie find(int idPartie) {
    	this.partieDAO.find(idPartie);
    }
    
    /**
     * Sauvegarde une partie
     * @param p partie à sauvegarder
     */
    public void save(Partie p){
    	this.partieDAO.save(p);
    	
    }
    
    /**
     * Récupère la liste des joueurs d'une partie
     * @return ArrayList<Utilisateur> liste des utilisateurs d'une partie
     */
    public ArrayList<Utilisateur> partieJoueurs(int idPartie) {
        ArrayList<Utilisateur> users = this.partieDAO.getJoueurs(idPartie);
        return users;
    }
    
    /**
     * Récupère le nombre de joueurs dans une partie
     * @param idPartie id de la partie
     * @return int le nombre de joueurs dans la partie
     */
    public int joueursDansPartie(int idPartie) {
    	return this.partieDAO.JoueursDansPartie(idPartie);
    }

}
