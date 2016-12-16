package fr.ascadis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import fr.ascadis.bean.Questionbean;
import fr.ascadis.model.Question;

/**
 * Controller Question
 * @author thoma
 *
 */
@Controller
@Scope("request")
public class QuestionController implements Serializable {
    

    private static final long serialVersionUID = 1L;

    
    /**
     * Injection de dépendences du bean de question
     */
    @Autowired
    private Questionbean questionbean;
    /**
     * Titre de la page
     */
    private String title;
    /**
     * Question éditée
     */
    private Question editedItem;

    public Question getEditedItem() {
        return editedItem;
    }
    public void setEditedItem(Question editedItem) {
        this.editedItem = editedItem;
    }
    /**
     * Initialisation du constructeur
     */
    @PostConstruct
    public void init() {
        if(this.editedItem == null) {
            String myId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("question_id");
            
            if(myId == null) {
                this.editedItem = new Question("La question....","La réponse....");
                this.setTitle("Nouvelle question");
            }else{
                this.editedItem = (Question)this.questionbean.getQuestion(Integer.parseInt(myId));
                this.setTitle("Edition question");
            }
            
        }
        
    }
    /**
     * Récupère la liste des questions
     * @return liste des questions
     */
    public List<Question> getQuestions() {
        List<Question> qs = this.questionbean.getQuestions();
        return  qs;
    }
    
    /**
     * Supprime une question
     * @param question
     */
    public void remove(Question question) {
         this.questionbean.removeQuestion(question);
    }
    
    /**
     * Sauvegarde une question
     * @return redirection vers la FAQ
     */
    public String validate() {
        
        this.questionbean.ajouterQuestion(this.editedItem);
        return "questions?faces-redirect=true";
        
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
