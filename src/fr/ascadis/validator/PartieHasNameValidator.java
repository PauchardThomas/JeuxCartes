package fr.ascadis.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("fr.ascadis.validator.PartieHasNameValidator")
public class PartieHasNameValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
    {
      FacesMessage myFacesMessage = null;
      UIInput myPartieComponent = (UIInput)component.getAttributes().get("component_user_password");
      String myPartieName = (String)myPartieComponent.getValue();
      String myPasswordCheckValue = (String)value;
      
      
      if (myPartieName == "")
      {
        myFacesMessage = new FacesMessage("Veuillez saisir un intitule pour votre partie...");
        myFacesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        
        throw new ValidatorException(myFacesMessage);
      }
    }

}
