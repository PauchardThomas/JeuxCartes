package fr.ascadis;

import java.util.List;

import fr.ascadis.model.Question;

public interface IQuestion {
	
	List<Question> getQuestions();
	
	void ajouterQuestion(Question question);
	
	void removeQuestion(int id);
	
	Question getQuestion(int id);

}
