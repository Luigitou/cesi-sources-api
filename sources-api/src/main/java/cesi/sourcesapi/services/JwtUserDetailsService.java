package cesi.sourcesapi.services;

import cesi.sourcesapi.repository.UtilisateurRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    final UtilisateurRepository userRepository;

    public JwtUserDetailsService(UtilisateurRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        cesi.sourcesapi.model.Utilisateur user = userRepository.findUserByUsername(username).get();
        List<GrantedAuthority> authorityList = new ArrayList<>();
        //authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
        return new User(user.getUsername(), user.getPassword(), authorityList);
    }

    public UserDetails createUserDetails(String username, String password) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        //authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
        return new User(username, password, authorityList);
    }
}
