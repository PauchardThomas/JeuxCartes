package fr.ascadis;

import java.util.List;

import fr.ascadis.model.Carte;

public interface IApplicationData
{
	List<Carte> getCartes();
	
	void ajouterCarte(Carte carte);
	
	void removeCarte(String id);
	
	Carte getCarte(String id);
}
