package fr.ascadis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.ascadis.IApplicationData;
import fr.ascadis.dao.CarteDAO;
import fr.ascadis.model.Carte;

@Component
@Scope("request")
public class SqlApplicationDataBean implements IApplicationData, Serializable {


    private static final long serialVersionUID = 1L;
    private Map<String,Carte> cartes=  null;
    @EJB
    private CarteDAO carteDAO;

    public void reset() {
        this.cartes = new HashMap<>();
        for(Carte carte : this.carteDAO.findAll()) {
            this.cartes.put(carte.getId(), carte);
        }
    }
    
    @Override
    public List<Carte> getCartes() {
        if (this.cartes == null)
        {
          this.reset();
        }
        return new ArrayList<>(this.cartes.values());
    }

    @Override
    public void ajouterCarte(Carte carte) {
        this.carteDAO.save(carte);
        this.reset();
        
    }

    @Override
    public void removeCarte(String id) {
        Carte carte = this.carteDAO.find(id);
        this.carteDAO.delete(carte);
        this.cartes.remove(id);
    }

    @Override
    public Carte getCarte(String id) {
        this.reset();
        Carte carte = this.carteDAO.find(id);
        return carte;
    }

}
