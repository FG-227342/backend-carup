package com.nero.carupapi;

import com.nero.carupapi.model.Servicio;
import com.nero.carupapi.service.ClienteService;
import com.nero.carupapi.service.ServicioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class CarupApiApplication {

	public static void main(String[] args) {
		ApplicationContext c = SpringApplication.run(CarupApiApplication.class, args);
		//ClienteRepository cliRe = c.getBean(ClienteRepository.class);
		//ClienteService cliServ = c.getBean(ClienteService.class);

		//Cliente b= new Cliente("User Test", "3.417.146-7", "Direccion Prueba 1", "095478451", "123541", 1, null, null, "Created by Test");
		//ServicioService se = c.getBean(ServicioService.class);
/*
		Servicio s = new Servicio();
		s.setIdTarea(Short.valueOf("1"));
		s.setIdSrv((long)1);
		s.setIdUsuario(1);
		s.setIdVehiculo((long)1);
		s.setPaisOrigen(Short.valueOf("1"));
		s.setCiudadOrigen(1);
		s.setCiudadDestino(1);
		s.setLocOrigen(1);
		s.setZona(Short.valueOf("1"));
		s.setCalleOrigen("Av italia");
		s.setNumPuertaOrigen("5045");
		s.setEsquinaOrigen("Alejandro Gallinal");
		s.setLatitud("2343254");
		s.setLongitud("23432");
		s.setCiudadDestino(2);
		s.setLocDestino(1);
		s.setCalleDestino("Luis A de Herrera");
		s.setIdFalla(Short.valueOf("1"));
		s.setObservaciones("obs");
		*/


		//cliRe.save(b);

		//cliServ.crearCliente(b);
	}

}
