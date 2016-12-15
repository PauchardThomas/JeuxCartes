package fr.ascadis.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("fr.ascadis.validator.UtilisateurUsernameValidator")
public class UtilisateurUsernameValidator implements Validator
{
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
	{
		FacesMessage myFacesMessage = null;
		
		if (!value.toString().equals("Albert"))
		{
			myFacesMessage = new FacesMessage("Echec de la vérification", "Pas le bon nom d'utilisateur");
			myFacesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			throw new ValidatorException(myFacesMessage);
		}
	}
}
