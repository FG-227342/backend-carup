package com.nero.carupapi;

import com.nero.carupapi.model.Cliente;
import com.nero.carupapi.repository.ClienteRepository;
import com.nero.carupapi.service.ClienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDate;

@SpringBootApplication
public class CarupApiApplication {

	public static void main(String[] args) {
		ApplicationContext c = SpringApplication.run(CarupApiApplication.class, args);
		//ClienteRepository cliRe = c.getBean(ClienteRepository.class);
		//ClienteService cliServ = c.getBean(ClienteService.class);

		//Cliente b= new Cliente("User Test", "3.417.146-7", "Direccion Prueba 1", "095478451", "123541", 1, null, null, "Created by Test");

		//cliRe.save(b);

		//cliServ.crearCliente(b);
	}

}
