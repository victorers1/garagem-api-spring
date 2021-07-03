package zup.garagem.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import zup.garagem.entity.Usuario;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*
Utilizando @DataJpaTest ajuda a configurar algumas coisas automagicamente:
* Configuração de H2 in-memory
* Spring Data, Datasource
* Modo de logar o SQL ON
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void insertUser(){
        Usuario usuario = new Usuario("Victor",
                "victor@gmail.com",
                "08398328428",
                new Date(96, Calendar.JUNE, 13));

        usuarioRepository.save(usuario);

        Integer countUsuarios = usuarioRepository.findAll().size();
        assertEquals(1, countUsuarios);

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findUsuarioByCpfOrEmail(usuario.getCpf(), usuario.getEmail());

        assertTrue(usuarioEncontrado.isPresent());
        assertEquals(usuario, usuarioEncontrado.get());

    }

    @Test
    public void findByCpfOrEmail(){
        Usuario usuario = new Usuario("Victor",
                "victor@gmail.com",
                "08398328428",
                new Date(1996, 06, 13));

        usuarioRepository.save(usuario);

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findUsuarioByCpfOrEmail(usuario.getCpf(), usuario.getEmail());

        assertTrue(usuarioEncontrado.isPresent());
        assertEquals(usuario, usuarioEncontrado.get());
    }
}
