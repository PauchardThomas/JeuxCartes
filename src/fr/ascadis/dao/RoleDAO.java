package fr.ascadis.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.ascadis.DAO;
import fr.ascadis.model.Question;
import fr.ascadis.model.Role;
import fr.ascadis.model.Utilisateur;

/**
 * Gestion des données des roles
 * @author thoma
 *
 */
@Repository
@Transactional
public class RoleDAO extends DAO<Role, Integer>{

    /**
     * Ebtity manager
     */
    @PersistenceContext
    EntityManager em;
    
    
    /**
     * Récupère un role
     * @param id identifiant ru role
     * @return Role role récuoéré
     */
    @Override
    public Role find(Integer id) {
        return em.find(Role.class, id);
    }

    /**
     * Récupère tout les roles
     * @return List<Role> liste des roles
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> findAll() {
        return (List<Role>)em.createQuery("select r from Role r").getResultList();
    }

    /**
     * Sauvegarde un role
     * @param role role à sauvegarder
     * @return role sauvegardé
     */
    @Override
    public Role save(Role role) {
        return em.merge(role);
    }

    /**
     * Supprime lun role
     * @param role role à supprimer
     */
    @Override
    public void delete(Role role) {
        em.remove(role);
    }
    

}
