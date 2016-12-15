package fr.ascadis.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.ascadis.DAO;
import fr.ascadis.model.Carte;
import fr.ascadis.model.Partie;
import fr.ascadis.model.PartieUtilisateurMain;
import fr.ascadis.model.Utilisateur;

/**
 * Gestion des données de la main d'un utilisateur
 * @author thoma
 *
 */
@Repository
@Transactional
public class PartieUtilisateurMainDAO extends DAO<PartieUtilisateurMain,Integer> {

    /**
     * Entity manager
     */
    @PersistenceContext
    EntityManager em;
    
    /**
     * Injection de dépendance de la gestion des cartes
     */
    @Autowired
    CarteDAO carteDAO;
    
    /**
     * Récupère une partie d'un utilisateur d'une partie
     */
    @Override
    public PartieUtilisateurMain find(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Récupère toutes les cartes d'un utilisateur pour une partie
     */
    @Override
    public List<PartieUtilisateurMain> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Récupère la liste des cartes d'un utilisateur pour une partie
     * @param user_id identifiant de l'utilisateur
     * @param partie_id identifiant de la partie
     * @return ArrayList<Carte> liste des carte de l'utilisateur pour une partie
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Carte> getUserPartieCartes(int user_id,int partie_id) {
        Query query = em.createQuery("select carte_id from PartieUtilisateurMain pum where user_id = :user_id and pum.partie_id = :partie_id ");
        query.setParameter("user_id",user_id);
        query.setParameter("partie_id",partie_id);
        ArrayList<Carte> userCartes = new ArrayList<>();
        ArrayList<String> cartes_id = (ArrayList<String>) query.getResultList();
        for(String i : cartes_id) {
            userCartes.add(carteDAO.find(i));
        }
        return userCartes;
    }

    /**
     * Sauvegarde une carte d'un utilisateur pour une partie
     */
    @Override
    public PartieUtilisateurMain save(PartieUtilisateurMain main) {
        //return em.merge(object);
        Query query = em.createNativeQuery("insert into partie_user_carte (user, partie, carte) values (:user, :partie, :carte)");
        query.setParameter("user",main.getUser_id());
        query.setParameter("partie",main.getPartie_id());
        query.setParameter("carte",main.getCarte_id());
        query.executeUpdate();
        return null;
        //return (PartieUtilisateurMain) query.getSingleResult();
    }
    
    /**
     * Compte les nombre de cartes dans la main de l'utilisateur pour la partie
     * @param object
     * @return
     */
    public int countCartesInMain(PartieUtilisateurMain main) {
        Query query = em.createNativeQuery("select count(*) from partie_user_carte where user =  :user AND partie = :partie");
        query.setParameter("user",main.getUser_id());
        query.setParameter("partie",main.getPartie_id());
        Object o = query.getSingleResult();
        
        return Integer.parseInt(o.toString());
    }

    /**
     * Supprime une carte d'un utilisateur pour une partie
     */
    @Override
    public void delete(PartieUtilisateurMain object) {
        em.remove(em.contains(object) ? object : em.merge(object));
        
    }

}
