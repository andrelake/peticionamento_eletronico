package com.andrelake.peticionamento;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import com.andrelake.peticionamento.domain.model.peticao.Foro;
import com.andrelake.peticionamento.domain.service.peticao.ForoService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class PeticionamentoApplicationTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private ForoService foroService;
	
	@BeforeEach
	public void setUp() {
		
		RestAssured.port = port;
		RestAssured.basePath = "/foros";
	}
	
	@Test
	public void deveConter2Foros_QuandoConsultarForos() {
		
		List<Foro> list = foroService.findAll();
		
		assertThat(list).hasSize(2);
	}
	
	@Test
	public void deveResponderStatus200_QuandoConsultarForos() {
		
		RestAssured
		.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(200);
	}
	
	@Test
	public void deveConterForo_QuandoConsultarForoPorId() {
		
		Foro foro = foroService.findById(1L);
		
		assertThat(foro).isNotNull();
		assertThat(foro.getId()).isNotNull();
	}
}
