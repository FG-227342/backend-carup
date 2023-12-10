package com.nero.carupapi.controller;

import com.nero.carupapi.model.JWTRequest;
import com.nero.carupapi.model.JWTResponse;
import com.nero.carupapi.model.Usuario;
import com.nero.carupapi.model.UsuariosMobile;
import com.nero.carupapi.security.config.CustomerUserDetails;
import com.nero.carupapi.security.jwt.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomerUserDetails customerUserDetails;
    private final JWTService jwtService;

    public AuthController(AuthenticationManager authenticationManager, CustomerUserDetails customerUserDetails, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.customerUserDetails = customerUserDetails;
        this.jwtService = jwtService;
    }
    @PostMapping("/api/authenticate")
    public ResponseEntity<?> postToken(@RequestBody JWTRequest request){
        this.authenticate(request);
        System.out.println(request.getPlayerId());
        final List<Object> resultado = this.customerUserDetails.cargarUsuario(request.getUsername(), request.getPlayerId());
       // final var userDetails = this.customerUserDetails.loadUserByUsername(request.getUsername());
        final var userDetails = (UserDetails) resultado.get(0);


        final var token = this.jwtService.generateToken(userDetails);
        if(resultado.get(1).getClass() == Usuario.class){
            Usuario usuario = (Usuario)resultado.get(1);
            return ResponseEntity.ok(new JWTResponse(token, usuario.getNombre()));
        } else{
            UsuariosMobile usuario = (UsuariosMobile)resultado.get(1);
            return ResponseEntity.ok(new JWTResponse(token, usuario.getNombre()));
        }

    }

    private void authenticate(JWTRequest jwtRequest){
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        }catch (BadCredentialsException | DisabledException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @GetMapping("/api/authenticate")
    public String test(){
        return "API Car-Up test Up!";
    }


}
