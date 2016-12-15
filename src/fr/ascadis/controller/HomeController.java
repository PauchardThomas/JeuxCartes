package fr.ascadis.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import fr.ascadis.dao.PartieDAO;
import fr.ascadis.model.Partie;
import fr.ascadis.model.Utilisateur;

/**
 * Controller du menu home.
 * @author thomas
 *
 */
@Controller
@Scope("request")
public class HomeController implements Serializable {
    

    private static final long serialVersionUID = 1L;


    /**
     *  Injection de d�pendences de la gestion des donn�es d'une partie
     */
    @Autowired
    private PartieDAO partieDAO;
    
    /**
     * R�cup�re le nom de l'utilisateur
     * @return
     */
    public String getNom() {
        Utilisateur u =  (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return u.getUsername();
    }
    
    /**
     * R�cup�re la liste des parties � rejoindre.
     * @return List<Partie liste de partie � rejoindre
     */
    public List<Partie> partieARejoindre() {
        
        return partieDAO.getPartieARejoindre();
        
    }
    
    

}
