package cl.trimons.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringSecurityApplication {

	
	private static final Logger log = LoggerFactory.getLogger(SpringSecurityApplication.class);


	
	public static void main(String[] args) {
		log.info("INICIANDO LA APLICACION");
		SpringApplication.run(SpringSecurityApplication.class, args);
		log.info("APLICACION LISTA PARA RECIBIR");
				
	}

}
