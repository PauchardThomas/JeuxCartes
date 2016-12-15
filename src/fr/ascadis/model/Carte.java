package fr.ascadis.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "carte")
public class Carte implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id d'une carte
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "CAR_ID")
    private String id;

    /**
     * Nom d'un carte
     */
    @Column(name = "CAR_nom")
    @NotNull
    private String nom;

    /**
     * Couleur d'une carte
     */
    @Column(name = "CAR_couleur")
    private String couleur;
    
    /**
     * Poids d'une carte
     */
    @Column(name = "CAR_poids")
    private int poids;

    /**
     * Carte joué par.
     */
    private int jouePar;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getPoids() {
        return this.poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public Carte() {

    }

    public Carte(String nom, String couleur, int poids) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.couleur = couleur;
        this.poids = poids;
    }

    public int getJouePar() {
        return jouePar;
    }

    public void setJouePar(int jouePar) {
        this.jouePar = jouePar;
    }
}
