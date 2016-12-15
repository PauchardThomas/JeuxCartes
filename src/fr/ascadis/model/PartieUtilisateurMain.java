package fr.ascadis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Main d'un utilisateur pour une partie
 * 
 * @author thoma
 *
 */
@Entity
@Table(name = "partie_user_carte")
public class PartieUtilisateurMain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identifiant de la partie
     */
    @Id
    @Column(name = "partie")
    private int partie_id;
    
    /**
     * Identifiant de l'utilisateur
     */
    @Id
    @Column(name = "user")
    private int user_id;
    
    /**
     * Identifiant de la carte
     */
    @Id
    @Column(name = "carte")
    private String carte_id;

    public int getPartie_id() {
        return partie_id;
    }

    public void setPartie_id(int partie_id) {
        this.partie_id = partie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCarte_id() {
        return carte_id;
    }

    public void setCarte_id(String carte_id) {
        this.carte_id = carte_id;
    }

    public PartieUtilisateurMain(int partie_id, int user_id, String carte_id) {
        super();
        this.partie_id = partie_id;
        this.user_id = user_id;
        this.carte_id = carte_id;
    }

    /*
     * @Id
     * 
     * @ManyToOne
     * 
     * @JoinColumn(name="partie") private Partie partie;
     * 
     * @Id
     * 
     * @ManyToOne
     * 
     * @JoinColumn(name="user") private Utilisateur user;
     * 
     * @Id
     * 
     * @ManyToOne
     * 
     * @JoinColumn(name="carte") private Carte carte;
     * 
     * public Partie getPartie() { return partie; } public void setPartie(Partie
     * partie) { this.partie = partie; } public Utilisateur getUser() { return
     * user; } public void setUser(Utilisateur user) { this.user = user; }
     * public Carte getCarte() { return carte; } public void setCarte(Carte
     * carte) { this.carte = carte; } public PartieUtilisateurMain(Partie
     * partie, Utilisateur user, Carte carte) { super(); this.partie = partie;
     * this.user = user; this.carte = carte; }
     */
    public PartieUtilisateurMain() {
    }

}
