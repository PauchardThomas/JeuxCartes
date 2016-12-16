package fr.ascadis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.ascadis.DAO;
import fr.ascadis.model.Carte;

@Repository
@Transactional
public class CarteDAO extends DAO<Carte, String> {

    
    /**
     * Entity manager
     */
    @PersistenceContext
    EntityManager em;
    
    /**
     * Récupère une carte
     * @param id identifiant d'une carte
     */
    @Override
    public Carte find(String id) {
       return em.find(Carte.class,id);
    }

    /**
     * Récupère toutes les cartes
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Carte> findAll() {
        return  (List<Carte>)em.createQuery("select c from Carte c ORDER BY c.poids ASC ").getResultList();
    }

    /**
     * Sauvegarde une carte
     * @param object une carte
     */
    @Override
    public Carte save(Carte carte) {
        return em.merge(carte);
    }
    
    /**
     * Récupère une liste de carte random.
     * @return List<Carte>
     */
    @SuppressWarnings("unchecked")
    public List<Carte> getCartesrandom() {
        return  (List<Carte>)em.createQuery("select c from Carte c order by RAND() ").getResultList();
    }

    /**
     * Supprime une carte
     * @param object une carte à supprimer
     */
    @Override
    public void  delete(Carte carte) {
        em.remove(em.contains(carte) ? carte : em.merge(carte));
    }

    
}
