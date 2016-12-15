package fr.ascadis.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("fr.ascadis.validator.UtilisateurPasswordCheckValidator")
public class UtilisateurPasswordCheckValidator implements Validator
{
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
	{
		FacesMessage myFacesMessage = null;
		UIInput myPasswordComponent = (UIInput)component.getAttributes().get("component_user_password");
		String myPasswordValue = (String)myPasswordComponent.getValue();
		String myPasswordCheckValue = (String)value;
		
		
		if (myPasswordValue != null && myPasswordCheckValue != null && !myPasswordValue.equals(myPasswordCheckValue))
		{
			myFacesMessage = new FacesMessage("Echec de la vérification", "Le mot de passe et sa confirmation doivent être identiques !");
			myFacesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			throw new ValidatorException(myFacesMessage);
		}
	}
}