package fr.ascadis.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.ascadis.DAO;
import fr.ascadis.model.Partie;
import fr.ascadis.model.Utilisateur;

/**
 * Gestion des données d'une partie
 * @author thoma
 *
 */
@Repository
@Transactional
public class PartieDAO extends DAO<Partie,Integer> {

    
    /**
     * Entity manager
     */
    @PersistenceContext
    EntityManager em;
    
    /**
     * Récupère une partie
     * @param id indentifiant d'une partie
     */
    @Override
    public Partie find(Integer partie_id) {
        Query query = em.createQuery("select p from Partie p where p.id = :id");
        query.setParameter("id", partie_id);
        return (Partie) query.getSingleResult();
    }

    /**
     * Récupère toutes les parties
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Partie> findAll() {
        return  (List<Partie>)em.createQuery("select p from Partie p ").getResultList();
    }
    
    /**
     * Récupère les partie à rejoindre.
     * @return List<Partie> la liste des parties a rejoindre.
     */
    @SuppressWarnings("unchecked")
    public List<Partie> getPartieARejoindre(){
        return (List<Partie>) em.createQuery("select p from Partie p where p.statut = 1 OR p.statut = 2").getResultList();
    }

    /**
     * Sauvegarde une partie
     * @param object une partie a sauvegarder
     */
    @Override
    public Partie save(Partie partie) {
        return em.merge(partie);
    }

    /**
     * Supprime une partie
     * @param partie à supprimer.
     */
    @Override
    public void delete(Partie partie) {
        em.remove(em.contains(partie) ? partie : em.merge(partie));
        
    }
    
    /**
     * Récupère le nombre de joueur dans une partie
     * @param id id de la partie
     * @return int nombre de joueur dans une partie
     */
    public int JoueursDansPartie(int id) {
        
        Query query = em.createQuery("select count(u) from Partie p join p.users u where p.id = :id");
        query.setParameter("id", id);
        return Integer.parseInt(query.getSingleResult().toString());
        
    }
    
    /**
     * Récupère tout les joueurs d'une partie
     * @param partie_id identifiant de la partie
     * @return ArrayList<Utilisateur> liste des utilisateurs d'une partie
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Utilisateur> getJoueurs(int partie_id) {
        Query query = em.createQuery("select u from Partie p join p.users u where p.id = :id ");
        query.setParameter("id", partie_id);
       Object o = query.getResultList();
        return (ArrayList<Utilisateur>) o;
    }
    
    

}
