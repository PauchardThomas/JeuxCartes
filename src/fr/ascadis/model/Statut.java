package fr.ascadis.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Statut d'une partie
 * @author thoma
 *
 */
@Entity
@Table(name = "statut")
public class Statut implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id du statut
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STA_ID")
    private int id;

    /**
     * Intitule du statut
     */
    @Column(name = "STA_INTITULE")
    private String intitule;

    /**
     * Liste des partie du statut
     */
    @OneToMany(mappedBy = "statut")
    private List<Partie> partie;

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getId() {
        return id;
    }

    public Statut(String intitule) {
        super();
        this.intitule = intitule;
    }

    public Statut() {
    }

}
