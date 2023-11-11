package com.nero.carupapi.service;

import com.nero.carupapi.dto.TurnoDTO;
import com.nero.carupapi.model.Chofer;
import com.nero.carupapi.model.Movil;
import com.nero.carupapi.model.Turno;
import com.nero.carupapi.repository.ChoferRepository;
import com.nero.carupapi.repository.MovilRepository;
import com.nero.carupapi.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private final TurnoRepository turnoRepo;
    private final ChoferRepository choferRepo;

    private final MovilRepository movilRepo;


    public TurnoService(TurnoRepository turnoRepo, ChoferRepository choferRepo, MovilRepository movilRepo) {
        this.turnoRepo = turnoRepo;
        this.choferRepo = choferRepo;
        this.movilRepo = movilRepo;
    }

    public Turno save(Turno turno){
        return turnoRepo.save(turno);
    }

    public List<Turno> findAll(){
        return turnoRepo.findAll();
    }

    public Optional<Turno> findById(Integer id){
        return turnoRepo.findById(id);
    }

    public void deleteById(Integer Id){
        turnoRepo.deleteById(Id);
    }

    public List<TurnoDTO> getAllDto(){
        List<Turno> turnos = turnoRepo.findAll();

        List<TurnoDTO> result = new ArrayList<>();

        turnos.forEach(t -> {

            // agrego solo los que tienen chofer asignado
            if(t.getIdChofer() != null){
                Integer idMovil = Integer.valueOf(t.getIdMovil());
                Optional<Movil> movil = movilRepo.findById(idMovil);

                TurnoDTO nuevo = new TurnoDTO();
                nuevo.setIdChofer(t.getIdChofer());
                nuevo.setIdMovil(t.getIdMovil());

                Integer idChof = Integer.valueOf(t.getIdChofer());
                Optional<Chofer> chof = choferRepo.findById(idChof);
                if(chof.isPresent()){
                    nuevo.setNombreChofer(chof.get().getNombre());

                    nuevo.setNombreMovil(movil.get().getNombre());
                    result.add(nuevo);
                }
            }

        });
        return result;
    }

}
