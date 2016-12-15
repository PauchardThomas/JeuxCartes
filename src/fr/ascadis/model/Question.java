package fr.ascadis.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Question pour la FAQ
 * 
 * @author thoma
 *
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identifiant d'une question
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "QUE_ID")
    private int id;

    /**
     * Intitule d'une question
     */
    @Column(name = "QUE_intitule")
    private String intitule;

    /**
     * Réponse à une question
     */
    @Column(name = "QUE_reponse", columnDefinition = "TEXT")
    private String reponse;

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String response) {
        this.reponse = response;
    }

    public int getId() {
        return id;
    }

    public Question(String intitule, String reponse) {
        super();
        this.intitule = intitule;
        this.reponse = reponse;
    }

    public Question() {
    }

}
