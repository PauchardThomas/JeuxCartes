package fr.ascadis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.ascadis.IQuestion;
import fr.ascadis.dao.QuestionDAO;
import fr.ascadis.model.Carte;
import fr.ascadis.model.Question;

@Component
@Scope(value="application")
public class Questionbean implements Serializable, IQuestion {

	/**
	 * Num�ro de serialisation du Bean
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Injection de d�pendence de la gestion des donn�es de question
	 */
	@Autowired
	private QuestionDAO questionDAO;


	/**
	 * R�cup�re la liste des questions
	 * @return List<Question> Liste des questions r�cup�res
	 */
	@Override
	public List<Question> getQuestions() {
	    return this.questionDAO.findAll();
	}

	/**
	 * Ajoute une question
	 * @param question question � ajouter
	 */
	@Override
	public void ajouterQuestion(Question question) {
	    this.questionDAO.save(question);

	}

	/**
	 * Supprime une question
	 * @param id id de la question � supprimer
	 */
	@Override
	public void removeQuestion(Question question) {
		this.questionDAO.delete(question);

	}

	/**
	 * R�cup�re une question
	 * @param id id de la questino � r�cup�re.
	 */
	@Override
	public Question getQuestion(int id) {
		return this.questionDAO.find(id);
	}

}
