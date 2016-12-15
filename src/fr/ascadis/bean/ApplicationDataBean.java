package fr.ascadis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.ascadis.IApplicationData;
import fr.ascadis.model.Carte;



@Component
@Scope(value="application")
public class ApplicationDataBean implements IApplicationData, Serializable
{
	private static final long serialVersionUID = 4916403474349802159L;
	private Map<String, Carte> cartes = new HashMap<>();
	
	
	@Override
	public List<Carte> getCartes()
	{
		return new ArrayList<>(this.cartes.values());
	}
	
	
	@Override
	public void ajouterCarte(Carte carte)
	{
		if (getCarte(carte.getId()) != null)
		{
			throw new IllegalArgumentException("La carte existe déjà");
		}
		
		this.cartes.put(carte.getId(), carte);
	}
	
	
	@Override
	public void removeCarte(String id)
	{
		this.cartes.remove(id);
	}
	
	
	@Override
	public Carte getCarte(String id)
	{
		return this.cartes.get(id);
	}
}
