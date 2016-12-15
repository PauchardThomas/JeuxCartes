package fr.ascadis.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "partie")
public class Partie implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identifiant d'une partie
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAR_ID")
    private int id;

    /**
     * Intitule d'une partie
     */
    @Column(name = "PAR_INTITULE")
    private String intitule;

    /**
     * Liste des utilisateurs d'une partie
     */
    @ManyToMany
    @Id
    @JoinTable(name = "partie_user", joinColumns = @JoinColumn(name = "partir_id", referencedColumnName = "PAR_ID"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "USER_ID"))
    private List<Utilisateur> users;

    /**
     * Gagnant d'une partie
     */
    @ManyToOne
    @JoinColumn(name = "gagnant")
    private Utilisateur gagnant;

    /**
     * Statut d'une partie
     */
    @ManyToOne
    @JoinColumn(name = "statut")
    private Statut statut;


    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<Utilisateur> getUsers() {
        return users;
    }

    public void setUsers(List<Utilisateur> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Utilisateur getGagnant() {
        return gagnant;
    }

    public void setGagnant(Utilisateur gagnant) {
        this.gagnant = gagnant;
    }

    public Partie(String intitule, List<Utilisateur> users, Utilisateur gagnant, Statut statut) {
        super();
        this.intitule = intitule;
        this.users = users;
        this.gagnant = gagnant;
        this.statut = statut;
    }

    public Partie() {
    }

}
