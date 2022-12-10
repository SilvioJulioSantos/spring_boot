package com.generationmeli.blogpessoal.repository;


import com.generationmeli.blogpessoal.model.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start(){

        /**
         * Apaga todos os registros do banco de dados antes de iniciar os testes
         */

        usuarioRepository.deleteAll();
        /**
         * Persiste (Grava) 4 Objetos Usuario no Banco de dados
         */

        usuarioRepository.save(new Usuario(0L, "Arthur do Julio", "arthur@email.com.br", "13465278", "https://i.imgur.com/FETvs2O.jpg"));

        usuarioRepository.save(new Usuario(0L, "Marcos Julio", "manuela@email.com.br", "13465278", "https://i.imgur.com/NtyGneo.jpg"));

        usuarioRepository.save(new Usuario(0L, "Fernando Julio", "adriana@email.com.br", "13465278", "https://i.imgur.com/mB3VM2N.jpg"));

        usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "13465278", "https://i.imgur.com/JR7kUFU.jpg"));


    }

    @Test
    @DisplayName("Retorna 1 usuario")
    public void deveRetornarUmUsuario() {


        Optional<Usuario> usuario = usuarioRepository.findByUsuario("arthur@email.com.br");

        assertTrue(usuario.get().getUsuario().equals("arthur@email.com.br"));
    }

    @Test
    @DisplayName("Retorna 3 usuarios")
    public void deveRetornarTresUsuarios() {

        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Julio");


        assertEquals(3, listaDeUsuarios.size());


        assertTrue(listaDeUsuarios.get(0).getNome().equals("Arthur do Julio"));


        assertTrue(listaDeUsuarios.get(1).getNome().equals("Marcos Julio"));



        assertTrue(listaDeUsuarios.get(2).getNome().equals("Fernando Julio"));

    }

    @AfterAll
    public void end() {
        usuarioRepository.deleteAll();
    }
}
