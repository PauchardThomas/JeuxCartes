package fr.ascadis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.ascadis.DAO;
import fr.ascadis.model.Question;

/**
 * Gestion des donn�es d'une question
 * @author thoma
 *
 */
@Repository
@Transactional
public class QuestionDAO extends DAO<Question,Integer> {

    /**
     * Entity manager 
     */
    @PersistenceContext
    EntityManager em;
    
    /**
     * R�cup�re une question
     * @param id identifiant de la question
     */
    @Override
    public Question find(Integer id) {
        return em.find(Question.class, id);
    }

    /**
     * R�cup�re toutes les questions
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Question> findAll() {
        return  (List<Question>)em.createQuery("select q from Question q ").getResultList();
    }

    /**
     * Sauvegarde une question
     * @param question question � sauvegarder
     */
    @Override
    public Question save(Question question) {
        return em.merge(question);
    }

    /**
     * Question � supprimer
     * @param question question � supprimer
     */
    @Override
    public void delete(Question question) {
        em.remove(em.contains(question) ? question : em.merge(question));
    }

}
