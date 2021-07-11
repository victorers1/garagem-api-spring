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

    private static final String NOME = "Victor";
    private static final String EMAIL = "victor@gmail.com";
    private static final String CPF = "08398328428";
    private static final Date DATA_NASCIMENTO = new Date(96, Calendar.JUNE, 13);

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void insertUser() {
        // Arrange
        Usuario usuario = new Usuario(
                NOME,
                EMAIL,
                CPF,
                DATA_NASCIMENTO
        );

        // Act
        usuarioRepository.save(usuario);
        Integer countUsuarios = usuarioRepository.findAll().size();
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findUsuarioByCpfOrEmail(usuario.getCpf(), usuario.getEmail());

        // Assert
        assertEquals(1, countUsuarios);
        assertTrue(usuarioEncontrado.isPresent());
        assertEquals(usuario, usuarioEncontrado.get());
    }

    @Test
    public void findByCpfOrEmail() {
        // Arrange
        Usuario usuario = new Usuario(
                NOME,
                EMAIL,
                CPF,
                DATA_NASCIMENTO
        );

        // Act
        usuarioRepository.save(usuario);
        Integer countUsuarios = usuarioRepository.findAll().size();
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findUsuarioByCpfOrEmail(usuario.getCpf(), usuario.getEmail());

        // Assert
        assertEquals(1, countUsuarios);
        assertTrue(usuarioEncontrado.isPresent());
        assertEquals(usuario, usuarioEncontrado.get());
    }
}
