package fr.ascadis.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


import fr.ascadis.Constantes;
import fr.ascadis.bean.SqlApplicationDataBean;
import fr.ascadis.dao.UtilisateurDAO;
import fr.ascadis.model.Carte;
import fr.ascadis.model.Utilisateur;

/**
 * Controller de cartes
 * @author thomas
 *
 */
@Controller
@Scope("request")
public class CartesController implements Serializable {
    private static final long serialVersionUID = -1700669412555079814L;

    /**
     * Injection de dépendences de de sqlapplicationdatabean
     */
    @Autowired
    private SqlApplicationDataBean sqlApplicationDataBean;

    /**
     * Injection de dépendences de la gestion des données d'un utilisateur
     */
    @Autowired
    private UtilisateurDAO utilisateurDAO;

    /**
     * Canal d'écoute
     */
    private final static String CHANNEL = "/notify";
    
    /**
     * Titre de la page
     */
    private String title;
    /**
     * Utilisateur
     */
    private Utilisateur user;
    /**
     * Carte en cours
     */
    private Carte editedItem;
    /**
     * Main d'un utilisateur
     */
    private List<Carte> userMain;

    public SqlApplicationDataBean getSqlApplicationDataBean() {
        return sqlApplicationDataBean;
    }

    public void setSqlApplicationDataBean(SqlApplicationDataBean sqlApplicationDataBean) {
        this.sqlApplicationDataBean = sqlApplicationDataBean;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Intiailisation du controller
     */
    @PostConstruct
    public void init() {
        String myId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("carte_id");
       this.user =  (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userMain = this.utilisateurDAO.getUserCartes(this.user);
        if (this.editedItem == null) {
            if (myId != null) {
                this.editedItem = this.sqlApplicationDataBean.getCarte(myId);
                this.title = "Edition Carte";
            }

            else {
                this.editedItem = new Carte("Pas de nom", "#000", 0);
                this.title = "Nouvelle carte";
            }
        }
    }

    /**
     * Récupère une liste de cartes.
     * @return ListwCarte> liste de cartes
     */
    public List<Carte> getCartes() {
        Boolean isMain = Boolean.parseBoolean(
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("main"));
        List<Carte> myCartes = null;

        if (isMain) {
            myCartes = new ArrayList<>();
            if (this.utilisateurDAO != null) {
                for (Carte carte : this.utilisateurDAO.getUserCartes(this.user)) {
                    myCartes.add(this.sqlApplicationDataBean.getCarte(carte.getId()));
                }
            }
        }

        else {
            myCartes = this.sqlApplicationDataBean.getCartes();
        }

        return myCartes;
    }

    /**
     * Vérification si la carte peut être insérée dans la main de l'utilisateur
     * @param carte carte ç insérer dans la main de l'utilisateur
     * @return Boolean , true si oui, false si non
     */
    public Boolean canInsertInMain(Carte carte) {

        return (!isInMain(carte) && (userMain.size() < Constantes.NB_MAX_CARTES_PAR_MAIN));
    }

    /**
     * Vérifie si la carte est dans la main de l'utilisateur
     * @param carte carte à vérifier
     * @return Boolean , true si oui, false si non
     */
    public Boolean isInMain(Carte carte) {
        for (Carte c : userMain) {
            if (c.getId().equals(carte.getId())) {
                return true;
            }
        }
        return false;
    }

    public Carte getEditedItem() {
        return this.editedItem;
    }

    public Boolean getMontrerActionsMain() {
        return !Boolean.parseBoolean(
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("main"));
    }

    public Boolean getMontrerActionsCarte() {
        return !Boolean.parseBoolean(
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("main"));
    }

    /**
     * Création d'une nouvelle carte
     * @return
     */
    public String validate() {

        this.sqlApplicationDataBean.ajouterCarte(this.editedItem);
        return "liste?faces-redirect=true";
    }

    /**
     * Supprime une carte
     * @param carte carte à supprimer.
     */
    public void remove(Carte carte) {
        if (this.isInMain(carte)) {
            Carte carterm = carte; 
            for(Carte c : userMain) {
                if(c.getId().equals(carte.getId())) {
                    carterm = c;
                }
            }
            userMain.remove(carterm);
        }
        this.user.setCartes(userMain);
        this.utilisateurDAO.save(this.user);
        this.sqlApplicationDataBean.removeCarte(carte.getId());
    }

    /**
     * Insère une carte dans la main d'un utilisateur
     * @param id identifiant de la carte à insérer.
     */
    public void toggleCarte(String id) {

        if (id != null) {
            userMain = this.utilisateurDAO.getUserCartes(this.user);
            Carte myCarte = this.sqlApplicationDataBean.getCarte(id);
            if (this.isInMain(myCarte)) {
                Carte carterm = myCarte; 
                for(Carte c : userMain) {
                    if(c.getId().equals(myCarte.getId())) {
                        carterm = c;
                    }
                }
                userMain.remove(carterm);
            } else {
                if (userMain.size() < Constantes.NB_MAX_CARTES_PAR_MAIN) {
                    userMain.add(myCarte);
                }

            }
            this.user.setCartes(userMain);
            this.utilisateurDAO.save(this.user);
        }
    }
    
    /**
     * Notification lorsqu'une carte est créée.
     */
    public void create() {
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage("Nouvelle carte disponible !"));
    }
}