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
	 * Numéro de serialisation du Bean
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Injection de dépendence de la gestion des données de question
	 */
	@Autowired
	private QuestionDAO questionDAO;


	/**
	 * Récupère la liste des questions
	 * @return List<Question> Liste des questions récupères
	 */
	@Override
	public List<Question> getQuestions() {
	    return this.questionDAO.findAll();
	}

	/**
	 * Ajoute une question
	 * @param question question à  ajouter
	 */
	@Override
	public void ajouterQuestion(Question question) {
	    this.questionDAO.save(question);

	}

	/**
	 * Supprime une question
	 * @param id id de la question à  supprimer
	 */
	@Override
	public void removeQuestion(Question question) {
		this.questionDAO.delete(question);

	}

	/**
	 * Récupère une question
	 * @param id id de la questino à récupère.
	 */
	@Override
	public Question getQuestion(int id) {
		return this.questionDAO.find(id);
	}

}
