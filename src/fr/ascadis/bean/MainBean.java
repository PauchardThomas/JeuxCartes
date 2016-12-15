package fr.ascadis.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")
public class MainBean implements Serializable
{
	private static final long serialVersionUID = -5146300351120852148L;
	private Set<String> cartes = new HashSet<>();
	
	public Set<String> getCartes() {
		return cartes;
	}
}
