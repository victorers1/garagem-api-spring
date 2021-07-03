package zup.garagem.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import zup.garagem.dto.UsuarioDTO;
import zup.garagem.entity.Usuario;
import zup.garagem.service.UsuarioService;
import zup.garagem.service.VeiculoService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UsuarioRestController.class)
public class UsuarioRestControllerTest {
    private static final String NOME = "Victor";
    private static final String EMAIL = "victor@gmail.com";
    private static final String CPF = "08398328428";
    private static final Date DATA_NASCIMENTO = new Date(96, Calendar.JUNE, 13);

    Usuario usuario;

    UsuarioDTO usuarioDTO;

    List<Usuario> usuarios;

    List<UsuarioDTO> usuariosDTO;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;
    @MockBean
    private VeiculoService veiculoService;

    @BeforeEach
    public void ArrangeVariables() {
        usuario = new Usuario(NOME, EMAIL, CPF, DATA_NASCIMENTO);

        usuarioDTO = new UsuarioDTO(0L, NOME, EMAIL, CPF, DATA_NASCIMENTO);

        usuarios = List.of(usuario);

        usuariosDTO = List.of(usuarioDTO);
    }

    @Test
    public void findAllUsuarios() throws Exception {
        // Arrange
        BDDMockito.given(usuarioService.findAllDTO()).willReturn(usuariosDTO);

        // Act
        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"));

        // Assert
        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(0))
                .andExpect(jsonPath("$[0].nome").value(NOME))
                .andExpect(jsonPath("$[0].email").value(EMAIL))
                .andExpect(jsonPath("$[0].cpf").value(CPF))
                .andExpect(jsonPath("$[0].dataNascimento").value("13-06-1996"));
    }
}
