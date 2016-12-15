package fr.ascadis.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.ascadis.DAO;
import fr.ascadis.model.Statut;

/**
 * Gestion des donn�es des statuts
 * @author thoma
 *
 */
@Repository
@Transactional
public class StatutDAO extends DAO<Statut,Integer>{

    /**
     * Entity manager
     */
    @PersistenceContext
    EntityManager em;
    
    /**
     * R�cup�re un statut
     * @param id identifiant d'un statut
     */
    @Override
    public Statut find(Integer id) {
       return em.find(Statut.class,id);
    }

    /**
     * R�cup�re la liste des statuts
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Statut> findAll() {
        return  (List<Statut>)em.createQuery("select s from Statut s ").getResultList();
    }

    /**
     * Sauvegarde un statut
     * @param statut statut � sauvegarder
     * @return statut sauvegarder
     */
    @Override
    public Statut save(Statut statut) {
        return em.merge(statut);
    }

    /**
     * Supprime un statut
     * @param statut statut � supprimer
     */
    @Override
    public void  delete(Statut statut) {
        em.remove(em.contains(statut) ? statut : em.merge(statut));
    }

    

}
