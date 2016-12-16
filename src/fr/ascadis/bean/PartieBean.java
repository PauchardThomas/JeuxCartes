package fr.ascadis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.ascadis.dao.PartieDAO;
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
	 * Injection de dépendances de la gestion des données d'une partie
	 */
	@Autowired
	private PartieDAO partieDAO;
	
    /**
     * Récupère la liste des parties à  rejoindre
     * @return List<Partie liste de partie à rejoindre
     */
    public List<Partie> partieARejoindre() {
        
        return this.partieDAO.getPartieARejoindre();
        
    }
    
    /**
     * Récupère une partie
     * @param idPartie id de la partie à récuperer
     * @return Partie partie récupérer
     */
    public Partie find(int idPartie) {
    	return this.partieDAO.find(idPartie);
    }
    
    /**
     * Sauvegarde une partie
     * @param p partie à  sauvegarder
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
