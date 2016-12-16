package fr.ascadis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ascadis.IQuestion;
import fr.ascadis.model.Carte;
import fr.ascadis.model.Question;

@Component
@Scope(value="application")
public class Questionbean implements Serializable, IQuestion {

	/**
	 * Numéro de serialisation du Bean
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, Question> questions = new HashMap<>();


	/**
	 * Récupère la liste des questions
	 * @return List<Question> Liste des question récupéré
	 */
	@Override
	public List<Question> getQuestions() {
		return new ArrayList<>(this.questions.values());
	}

	/**
	 * AJoute une question
	 * @param question question à ajouter
	 */
	@Override
	public void ajouterQuestion(Question question) {
		if (getQuestion(question.getId()) != null)
		{
			throw new IllegalArgumentException("La question existe déjà");
		}
		
		this.questions.put(question.getId(), question);

	}

	/**
	 * Supprime une question
	 * @param id id de la question à supprimer
	 */
	@Override
	public void removeQuestion(int id) {
		this.questions.remove(id);

	}

	/**
	 * Récupère une question
	 * @param id id de la questino à récupérer.
	 */
	@Override
	public Question getQuestion(int id) {
		return this.questions.get(id);
	}

}
