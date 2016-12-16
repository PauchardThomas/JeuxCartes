package fr.ascadis.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.ascadis.controller.Autowired;
import fr.ascadis.dao.UtilisateurDAO;
import fr.ascadis.model.Carte;
import fr.ascadis.model.Utilisateur;

/**
 * Bean d'un utilisateur
 * @author tpauchard
 *
 */
@Component
@Scope(value="request")
public class UtilisateurBean implements Serializable
{

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Injection de d�pendences de la gestion des donn�es d'un utilisateur
	 */
	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	/**
	 * Sauvegarde un utilisateur
	 * @param u Utilisateur a sauvegarder
	 */
	public void saveUser(Utilisateur u){
		this.utilisateurDAO.save(u);
	}
	
	/**
	 * Récupère un utilisateur par ses logins
	 * @param username username de l'utilisateur
	 * @param password password de l'utilisateur
	 * @return Utilisateur trouvé.
	 */
	public Utilisateur FindByLogin(String username,String password) {
		return this.utilisateurDAO.findByLogin(username,password);
	}
	
	/**
	 * Récupère une liste de cartes d'un utilisateur
	 * @param u Utilisateur 
	 * @return List<Carte> liste des cartes d'un utilisateur
	 */
	public List<Carte> getUserCartes(Utilisateur u) {
		return this.utilisateurDAO.getUserCartes(u);
	}
	
	/**
	 * Recherche si un utilisateur est dans une liste d'utilisateurs
	 * @param users liste d'utilisateur
	 * @param user utilisateur
	 * @return Boolean , true s'il est dans la liste, false si non.
	 */
    public Boolean isInUsersList(List<Utilisateur> users,Utilisateur user) {
        for(Utilisateur u : users) {
            if(u.getId() == user.getId()) {
                return true;
            }
        }
        return false;
    }
	
	
	
	

}