package zup.garagem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.garagem.dto.UsuarioDTO;
import zup.garagem.entity.Usuario;
import zup.garagem.repository.UsuarioRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsuarioServiceTest {

    private static final String NOME = "Victor";
    private static final String EMAIL = "victor@gmail.com";
    private static final String CPF = "08398328428";
    private static final Date DATA_NASCIMENTO = new Date(96, Calendar.JUNE, 13);

    @Autowired
    UsuarioService usuarioService;

    @MockBean
    UsuarioRepository usuarioRepository;

    Usuario usuario;

    UsuarioDTO usuarioDTO;

    UsuarioDTO usuarioDTONaoExiste;

    List<Usuario> usuarios;

    @BeforeEach
    public void ArrangeVariables() {
        usuario = new Usuario(NOME,
                EMAIL,
                CPF,
                DATA_NASCIMENTO);

        usuarioDTO = new UsuarioDTO(0L,
                NOME,
                EMAIL,
                CPF,
                DATA_NASCIMENTO);

        usuarioDTONaoExiste = new UsuarioDTO(0L,
                "Emanuel",
                "emanuel@gmail.com",
                "12312312312",
                new Date(2000, Calendar.APRIL, 1));

        usuarios = List.of(usuario);
    }

    @Test
    public void UsuarioToDTO() {
        //Act
        UsuarioDTO usuarioDTO = usuarioService.toDTO(usuario);

        // Assert
        assertEquals(NOME, usuarioDTO.getNome());
        assertEquals(EMAIL, usuarioDTO.getEmail());
        assertEquals(CPF, usuarioDTO.getCpf());
        assertEquals(DATA_NASCIMENTO, usuarioDTO.getDataNascimento());
    }

    @Test
    public void findAllDTO() {
        // Arrange
        BDDMockito.given(usuarioRepository.findAll()).willReturn(usuarios);

        // Act
        List<UsuarioDTO> usuariosDTO = usuarioService.findAllDTO();
        UsuarioDTO usuarioDTO = usuariosDTO.get(0);

        // Assert
        assertEquals(UsuarioDTO.class, usuarioDTO.getClass());
        assertEquals(1, usuariosDTO.size());
        assertEquals(NOME, usuarioDTO.getNome());
        assertEquals(EMAIL, usuarioDTO.getEmail());
        assertEquals(CPF, usuarioDTO.getCpf());
        assertEquals(DATA_NASCIMENTO, usuarioDTO.getDataNascimento());
    }

    @Test
    public void existe() {
        //Arrange
        BDDMockito.given(usuarioRepository.findUsuarioByCpfOrEmail("08398328428", "victor@gmail.com")).willReturn(Optional.of(usuario));
        BDDMockito.given(usuarioRepository.findUsuarioByCpfOrEmail(Mockito.argThat(cpf -> cpf != "08398328428"), Mockito.argThat(email -> email != "victor@gmail.com"))).willReturn(Optional.empty());

        // Act
        Boolean existe = usuarioService.existe(usuarioDTO);
        Boolean naoExiste = usuarioService.existe(usuarioDTONaoExiste);

        // Assert
        assertTrue(existe);
        assertFalse(naoExiste);
    }
}
