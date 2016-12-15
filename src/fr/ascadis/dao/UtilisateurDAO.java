package fr.ascadis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.ascadis.DAO;
import fr.ascadis.model.Carte;
import fr.ascadis.model.Utilisateur;

/**
 * Gestion des données d'un utilisateur
 * @author thoma
 *
 */
@Repository
@Transactional
public class UtilisateurDAO extends DAO<Utilisateur, Integer> {

    /**
     * Entity manager
     */
    @PersistenceContext
    EntityManager em;
    
    /**
     * Récupère un utilisateur son username et password
     * @param username username de l'utilisateur
     * @param password password de l'utilisateur
     * @return Utilisateur utilisateur récupéré
     */
    public Utilisateur findByLogin(String username,String password) {
         Query query =  em.createQuery("select u from Utilisateur u where u.username = :username and u.password = :password");
        query.setParameter("username",username);
        query.setParameter("password",password);
        
        return (Utilisateur)query.getSingleResult();
    }

    /**
     *  Récupère tout les utilisateurs
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Utilisateur> findAll() {
        return (List<Utilisateur>)em.createQuery("select u from Utilisateur u").getResultList();
    }

    /**
     * Sauvegarde un utilisateur
     * @param utilisateur Utilisateur à sauvegarder
     * @return utilisateur sauvegardé
     */
    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        return em.merge(utilisateur);
    }

    /**
     * Supprime un utilisateur
     * @param utilisateur utilisateur à supprimer
     */
    @Override
    public void delete(Utilisateur utilisateur) {
        em.remove(utilisateur);
        
    }

    /**
     * Récupère un utilisateur
     * @param id identifiant de l'utilisateur
     */
    @Override
    public Utilisateur find(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Récupère les cartes d'un utilisateur
     * @param utilisateur utilisateur
     * @return ArrayList<Carte> liste des cartes d'un utilisateur
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Carte> getUserCartes(Utilisateur utilisateur) {
        Query query = em.createQuery("select u.cartes from Utilisateur u where u.id = :id ");
        query.setParameter("id", utilisateur.getId());
        return(ArrayList<Carte>) query.getResultList();
    }
    /**
     *  Check si l'utilisateur est dan sune liste d'utilisateurs
     * @param users liste d'utilisateurs
     * @param user utilisateur
     * @return boolean true si dans la liste false si non
     */
    public Boolean isInUsersList(List<Utilisateur> users,Utilisateur user) {
        for(Utilisateur u : users) {
            if(u.getId() == user.getId()) {
                return true;
            }
        }
        return false;
    }

}
