package com.nero.carupapi.security.config;

import com.nero.carupapi.model.Usuario;
import com.nero.carupapi.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerUserDetails implements UserDetailsService {

    private final UsuarioRepository usrRepo;

    public CustomerUserDetails(UsuarioRepository usrRepo) {
        this.usrRepo = usrRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usrRepo.findByEmail(email)
                .map(usr-> {
                    var authorities = List.of(new SimpleGrantedAuthority("ADMIN"));
                  return new User(usr.getEmail(),usr.getClave(),authorities);
                }).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
