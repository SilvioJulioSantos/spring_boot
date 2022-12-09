package com.generationmeli.blogpessoal.controller;

import com.generationmeli.blogpessoal.model.Usuario;
import com.generationmeli.blogpessoal.repository.UsuarioRepository;
import com.generationmeli.blogpessoal.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTeste {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioService userservice;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void starr(){
        usuarioRepository.deleteAll();
        userservice.cadastrarUsuario(new Usuario(0L,
                "Root","root@root.com","rootroot",""));
    }

    @Test
    @DisplayName("Cadastrar um Usuário")
    public void deveCriarUmUsuario() {
        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(0L,
                "Luiz Silva","luizsilva@gmail.com","456123",""));
        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .exchange("/usuario/cadastrar", HttpMethod.POST,corpoRequisicao, Usuario.class);

        assertEquals(HttpStatus.CREATED,corpoResposta.getStatusCode());
        assertEquals(corpoRequisicao.getBody().getNome(),corpoResposta.getBody().getNome());
        assertEquals(corpoRequisicao.getBody().getUsuario(),corpoResposta.getBody().getUsuario());
    }
    @Test
    @DisplayName("Listar todos os Usuários")
    public void deveMostraTodas(){{
       userservice.cadastrarUsuario(new Usuario(0L,
               "Luiz Silva","luizsilva@gmail.com","456123",""));
        userservice.cadastrarUsuario(new Usuario(0L,
                "Marcos Felipe","marcosfelipe@gmail.com","789159",""));

        ResponseEntity<String> respota = testRestTemplate
                .withBasicAuth("root@root.com","rootroot")
                .exchange("usuario/all",HttpMethod.GET,null, String.class);
        assertEquals(HttpStatus.OK, respota.getStatusCode());
     }
    }
}
