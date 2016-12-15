package fr.ascadis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Utilisateur
 * 
 * @author thoma
 *
 */
@Entity
@Table(name = "user")
public class Utilisateur implements Serializable, UserDetails {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identifiant d'un utilisateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;

    /**
     * Nom d'un utilisateur
     */
    @Column(name = "nom")
    private String nom;

    /**
     * Prénom d'un utilisateur
     */
    @Column(name = "prenom")
    private String prenom;

    /**
     * Username d'un utilisateur
     */
    @Column(name = "username")
    private String username;

    /**
     * Password d'un utilisateur
     */
    @Column(name = "password")
    private String password;

    /**
     * Liste des partie d'un utilisateur
     */
    @OneToMany(mappedBy = "gagnant")
    private List<Partie> parties_win;

    /*
     * @OneToMany(mappedBy="user") private List<PartieUtilisateurMain>
     * partieUserMain;
     */

    @ManyToMany
    @JoinTable(name = "carte_utilisateur", joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "carte_id", referencedColumnName = "CAR_ID"))
    private List<Carte> cartes;

    @ManyToMany
    @JoinTable(name = "role_utilisateur", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "ROL_ID"))
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}