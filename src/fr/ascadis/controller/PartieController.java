package fr.ascadis.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import fr.ascadis.dao.CarteDAO;
import fr.ascadis.dao.PartieDAO;
import fr.ascadis.dao.PartieUtilisateurMainDAO;
import fr.ascadis.dao.StatutDAO;
import fr.ascadis.dao.UtilisateurDAO;
import fr.ascadis.model.Carte;
import fr.ascadis.model.Partie;
import fr.ascadis.model.PartieUtilisateurMain;
import fr.ascadis.model.Utilisateur;

/**
 * Controller d'une partie
 * @author thoma
 *
 */
@Controller
@Scope("request")
public class PartieController implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Titre de la page
     */
    private String title;
    /**
     * Partie edit�
     */
    private Partie editedItem;
    
    /**
     * Liste des joueurs de la partie
     */
    private ArrayList<Utilisateur> joueurs;
    
    /**
     * Nombre de cartes par joueurs
     */
    private int NbCarteJoueur = 0;
    /**
     * Notification
     */
    private EventBus eventBus;
    /**
     * Canal d'�coute
     */
    private final static String CHANNEL = "/partie";

    /**
     * Injection de d�pendence de la gestion de donn�es d'une partie
     */
    @Autowired
    private PartieDAO partieDAO;

    /**
     * Injection de d�pendence de la gestion de donn�es d'un statut
     */
    @Autowired
    private StatutDAO statutDAO;

    /**
     * Injection de d�pendence de la gestion de donn�es d'un utilisateur
     */
    @Autowired
    private UtilisateurDAO utilisateurDAO;
    
    /**
     * Injection de d�pendence de la gestion de carte
     */
    @Autowired
    private CarteDAO carteDAO;
    
    /**
     * Injection de d�pendence de la gestion de donn�es d'e la main de l'utilisateur
     */
    @Autowired PartieUtilisateurMainDAO mainDAO;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Partie getEditedItem() {
        return editedItem;
    }

    public void setEditedItem(Partie editedItem) {
        this.editedItem = editedItem;
    }
    
    public int getNbCarteJoueur() {
        return NbCarteJoueur;
    }

    public void setNbCarteJoueur(int nbCarteJoueur) {
        NbCarteJoueur = nbCarteJoueur;
    }

    /**
     * Initialisation du controller.
     */
    @PostConstruct
    private void init() {
        String myId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("partie_id");
        if (myId == null) {
            this.title = "Creation d'une partie";
            this.editedItem = new Partie();
        } else {

            int idPartie = Integer.parseInt(myId);
            this.editedItem = partieDAO.find(idPartie);
            Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Utilisateur> users = partieJoueurs();

            if (shouldAddPlayer()) {
                
                if (!utilisateurDAO.isInUsersList(users, user)) {
                    users.add(user);
                    if(users.size() == 2) {
                        //Lancement de la partie
                        this.editedItem.setStatut(this.statutDAO.find(2));
                    }
                    this.editedItem.setUsers(users);
                    this.partieDAO.save(this.editedItem);
                    
                    if(users.size() >= 2) {
                        this.joueurs = this.partieJoueurs();
                        this.NbCarteJoueur = (this.distribution() / 2);
                        //this.create();
                        this.create();
                        this.NbCartes();
                    }
                    
                }
            }
            int t = statutPartie();
            this.title = "Partie : " + this.editedItem.getIntitule();
            
        }

    }

    /**
     * R�cup�re la liste des joueurs d'une partie
     * @return ArrayList<Utilisateur> liste des utilisateurs d'une partie
     */
    public ArrayList<Utilisateur> partieJoueurs() {
        ArrayList<Utilisateur> users = this.partieDAO.getJoueurs(this.editedItem.getId());
        return users;
    }
    
    /**
     * R�cup�re la position d'un utilisateur ( joueur 1 ou 2) 
     * @return String Position de l'utilisation
     */
    public String WhoAmI() {
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int i = 1;
        for(Utilisateur u : this.partieJoueurs()) {
            if(u.getId() == user.getId() ) {
                return String.valueOf(i);
            }
            i++;
        }
         return String.valueOf(i);
    }

    /**
     * Cr�ation d'une partie
     * @return String redirection vers la page home
     */
    public String validate() {
        this.editedItem.setStatut(this.statutDAO.find(1));
        this.partieDAO.save(this.editedItem);
        return "/WEB-INF/home?faces-redirect=true";
    }

    /**
     * V�rifie si on peut ajouter un joueur a une partie
     * @return Boolean , true si on peut ajouter le joueur , false si non
     */
    public Boolean shouldAddPlayer() {

        if (partieDAO.JoueursDansPartie(this.editedItem.getId()) > 2) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * R�cup�re le statut d'une partie
     * @return int statut d'une parite
     */
    public int statutPartie() {
        int statut = this.editedItem.getStatut().getId();
        return statut;
    }
    
    /**
     * Cr�ation du canal d'�coute
     */
    public void create() {
        this.eventBus = EventBusFactory.getDefault().eventBus();
    }
    
    /**
     * Notifie le nombre de cartes pour chaque joueurs
     */
    public void NbCartes() {
        this.eventBus.publish(CHANNEL,this.NbCarteJoueur);
    }
    
    /**
     * Distribution des cartes
     * @return int nombre de cartes
     */
    public int distribution() {
        // R�cup�re toutes les cartes.
        List<Carte> cartes = this.carteDAO.getCartesrandom();
        // Si ce n'est pas un nombre pair. on retire la derni�re.
        if(cartes.size()% 2 != 0) {
            cartes.remove(cartes.size() -1);
        }
        int i = 0;
        // Pour chaque utilisateur
        for(Utilisateur u : this.joueurs) {
            
            Utilisateur newU = new  Utilisateur();
            newU.setId(u.getId());
            newU.setUsername(u.getUsername());
            newU.setPassword(u.getPassword());
            while(i < cartes.size() /2) {
                PartieUtilisateurMain main = new PartieUtilisateurMain();
                main.setPartie_id(this.editedItem.getId());
                main.setUser_id(newU.getId());
                main.setCarte_id(cartes.get(i).getId());
                mainDAO.save(main);
                i++;
            }
            if(i > cartes.size() / 2) {
                int j = i -1;
                while(i <= cartes.size()) {
                    PartieUtilisateurMain main = new PartieUtilisateurMain();
                    main.setPartie_id(this.editedItem.getId());
                    main.setUser_id(newU.getId());
                    main.setCarte_id(cartes.get(j).getId());
                    mainDAO.save(main);
                    i++;
                    j++;
                }
            }
            i++;
        }
        
        joueurs.get(0).setCartes((ArrayList<Carte>) this.mainDAO.getUserPartieCartes(this.joueurs.get(0).getId(),this.editedItem.getId()));
        joueurs.get(1).setCartes((ArrayList<Carte>) this.mainDAO.getUserPartieCartes(this.joueurs.get(1).getId(),this.editedItem.getId()));
        
        
        return cartes.size();
    }
    
    /**
     * Notifie la carte jou� par un utilisateur
     */
    public void jouerCarte() {
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        ArrayList<Carte> main = mainDAO.getUserPartieCartes(user.getId(), this.editedItem.getId());
        if(main != null) {
            user.setCartes(mainDAO.getUserPartieCartes(user.getId(), this.editedItem.getId()));
            int rd = ThreadLocalRandom.current().nextInt(0, user.getCartes().size());
            Carte cartejoue = user.getCartes().get(rd);
            this.create();
            cartejoue.setJouePar(1);
            if(this.partieJoueurs().get(0).getId() != user.getId()) {
                cartejoue.setJouePar(2);
            }
            this.eventBus.publish(CHANNEL,cartejoue);
        }

    }


}
