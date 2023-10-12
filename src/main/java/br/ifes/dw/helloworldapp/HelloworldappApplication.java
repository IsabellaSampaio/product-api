package br.ifes.dw.helloworldapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Produto API", version = "1.0", description = "Crud de produtos em arquivo e em Banco de dados"))
public class HelloworldappApplication {

	public static void main(String[] args) {SpringApplication.run(HelloworldappApplication.class, args);}

}
