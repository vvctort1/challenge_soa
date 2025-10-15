package br.com.challenge.secondNature.SecondNatureSpringBoot.service;

import br.com.challenge.secondNature.SecondNatureSpringBoot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = repository.findByEmailAndAtivoTrue(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado ou inativo");
        }
        return user;
    }
}