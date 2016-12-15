package fr.ascadis.bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @PersistenceContext
    EntityManager em;

    
    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
         return (UserDetails)this.em.createQuery("from Utilisateur u left join fetch u.roles  where u.username = :username")
                 .setParameter("username", arg0).getSingleResult();
        
    }

}
