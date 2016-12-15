package fr.ascadis.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import fr.ascadis.model.Role;
import fr.ascadis.dao.RoleDAO;
import fr.ascadis.dao.UtilisateurDAO;
import fr.ascadis.model.Utilisateur;

/**
 * Controller de connexion/inscription
 * @author thoma
 *
 */
@Controller
@Scope("request")
public class AccountController implements Serializable
{
    
	private static final long serialVersionUID = -1758224779254088436L;
	/**
	 * Utilisateur
	 */
	private Utilisateur utilisateur;
	
	/**
	 * Injection de dépendences de la gestion des données d'un utilisateur
	 */
	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	/**
	 * Injection de dépendences de la gestion des données d'un rôle
	 */
	@Autowired
	private RoleDAO roleDAO;
	
	/**
	 * Check du password
	 */
	private String passwordCheck;
	
/**
 * Injection de dépendences de l'authentification manager
 */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	public String getPasswordCheck() {
		return passwordCheck;
	}
	
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	
	
	
	/**
	 * Initialisation du controller
	 */
	@PostConstruct
	public void init()
	{
		this.utilisateur = new Utilisateur();
	}
	
	
	/**
	 * Inscription au site.
	 * @return String page de connexion
	 */
	public String subscribe()
	{
      List<Role> roles = new ArrayList<>();
      roles.add(this.roleDAO.find(1));
      this.utilisateur.setRoles(roles);
	    this.utilisateurDAO.save(this.utilisateur);
		
		return "login?faces-redirect=true";
	}
	
	
	/**
	 * Connexion au site
	 * @return String vers home.
	 */
	public String login()
	{

	    FacesContext myContext = FacesContext.getCurrentInstance();
	    Utilisateur myUtilisateur = this.utilisateurDAO.findByLogin(this.utilisateur.getUsername(), this.utilisateur.getPassword());
	    
	    if (myUtilisateur != null)
	    {
	      Authentication myAuthentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(this.utilisateur.getUsername(), this.utilisateur.getPassword()));
	      if (myAuthentication.isAuthenticated())
	      {
	        SecurityContextHolder.getContext().setAuthentication(myAuthentication);
	        myContext.getExternalContext().getSessionMap().put("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
	        return "/WEB-INF/home?faces-redirect=true";
	      }
	      
	    }
	    return "login";
	}
	
	
	/**
	 * Déconnexion d'un utilisateur
	 * @return retour vers la page de connexion
	 */
	public String logout()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "account/login?faces-redirect=true";
	}

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}