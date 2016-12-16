package fr.ascadis;

import java.util.List;

import fr.ascadis.model.Question;

public interface IQuestion {
	
	List<Question> getQuestions();
	
	void ajouterQuestion(Question question);
	
	
	Question getQuestion(int id);

    void removeQuestion(Question question);

}
