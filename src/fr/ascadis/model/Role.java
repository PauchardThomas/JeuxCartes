package fr.ascadis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role d'un utilisateur
 * @author thoma
 *
 */
@Entity
@Table(name = "role")
public class Role implements Serializable, GrantedAuthority  {


    private static final long serialVersionUID = 1L;
    
    /**
     * Identifiant d'un utilisateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_ID")
    private int id;
    
    /**
     * Rang d'une utilisateur
     */
    @Column(name = "ROL_RANG")
    private String rang;
    


    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public int getId() {
        return id;
    }

    public Role(String rang) {
        super();
        this.rang = rang;
    }

    public Role() {}

    @Override
    public String getAuthority() {
        return this.rang;
    }

}
