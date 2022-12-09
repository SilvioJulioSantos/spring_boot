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

        usuarioRepository.deleteAll();

        usuarioRepository.save(new Usuario(0L, "Ranato Santos", "renatocalos@email.com.br", "159753", "https://i.imgur.com/FETvs2O.jpg"));

        usuarioRepository.save(new Usuario(0L, "Núbia Santos", "nubiasantos@email.com.br", "789024", "https://i.imgur.com/NtyGneo.jpg"));

        usuarioRepository.save(new Usuario(0L, "Michelle Santos", "michellesantos@email.com.br", "842957", "https://i.imgur.com/mB3VM2N.jpg"));

        usuarioRepository.save(new Usuario(0L, "Arthur Julio", "arthurjulio@email.com.br", "309759", "https://i.imgur.com/JR7kUFU.jpg"));

    }

    @Test
    @DisplayName("Retorna 1 usuario")
    public void deveRetornarUmUsuario() {

        Optional<Usuario> usuario = usuarioRepository.findByUsuario("arthurjulio@email.com.br");

        assertTrue(usuario.get().getUsuario().equals("arthurjulio@email.com.br"));
    }

    @Test
    @DisplayName("Retorna 3 usuarios")
    public void deveRetornarTresUsuarios() {

        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Santos");

        assertEquals(3, listaDeUsuarios.size());

        assertTrue(listaDeUsuarios.get(0).getNome().equals("Ranato Santos"));
        assertTrue(listaDeUsuarios.get(1).getNome().equals("Michelle Santos"));
        assertTrue(listaDeUsuarios.get(2).getNome().equals("Núbia Santos"));

    }


    @AfterAll
    public void end() {
        usuarioRepository.deleteAll();
    }

}
