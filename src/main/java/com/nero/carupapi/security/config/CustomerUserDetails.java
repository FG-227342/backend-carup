package com.nero.carupapi.security.config;

import com.nero.carupapi.model.Usuario;
import com.nero.carupapi.model.UsuariosMobile;
import com.nero.carupapi.repository.UsuarioRepository;
import com.nero.carupapi.repository.UsuariosMobileRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerUserDetails implements UserDetailsService {

    private final UsuarioRepository usrRepo;

    private final UsuariosMobileRepository userMobRepo;

    private Object usuarioFinal;

    public CustomerUserDetails(UsuarioRepository usrRepo, UsuariosMobileRepository userMobRepo) {
        this.usrRepo = usrRepo;
        this.userMobRepo = userMobRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> userEntityOptional = usrRepo.findByEmail(email);
        if (userEntityOptional.isPresent()) {
            usuarioFinal = userEntityOptional.get();
            return userEntityOptional.map(usr-> {
                var authorities = List.of(new SimpleGrantedAuthority(usr.getRol().getNombre()));
                return new User(usr.getEmail(),usr.getClave(),authorities);
            }).orElseThrow(()->new UsernameNotFoundException("User not found"));
        }

        Optional<UsuariosMobile> adminEntityOptional = userMobRepo.findByNombre(email);
        usuarioFinal = adminEntityOptional.get();
        return adminEntityOptional.map(usr-> {
            var authorities = List.of(new SimpleGrantedAuthority("MOBILE"));
            return new User(usr.getNombre(),usr.getClave(),authorities);
        }).orElseThrow(()->new UsernameNotFoundException("User not found"));
        /*
        return usrRepo.findByEmail(email)
                .map(usr-> {
                    var authorities = List.of(new SimpleGrantedAuthority(usr.getRol().getNombre()));
                  return new User(usr.getEmail(),usr.getClave(),authorities);
                }).orElseThrow(()->new UsernameNotFoundException("User not found"));
                */

    }

    public List<Object> cargarUsuario(String email, String playerId){
        List<Object> res = new ArrayList<>() {
        };

        res.add(loadUserByUsername(email));
        res.add(usuarioFinal);
        if(usuarioFinal.getClass() == UsuariosMobile.class) {
            UsuariosMobile usr = (UsuariosMobile) this.usuarioFinal;
            usr.setPlayerId(playerId);
            userMobRepo.save(usr);
        }
        return res;
    }
}
