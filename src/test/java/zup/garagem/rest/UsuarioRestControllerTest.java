package zup.garagem.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import zup.garagem.dto.UsuarioDTO;
import zup.garagem.entity.Usuario;
import zup.garagem.service.UsuarioService;
import zup.garagem.service.VeiculoService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: aprender testes de unidades em Controllers usando Mockito

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UsuarioRestController.class)
public class UsuarioRestControllerTest {
    private static final String NOME = "Victor";
    private static final String EMAIL = "victor@gmail.com";
    private static final String CPF = "08398328428";
    private static final Date DATA_NASCIMENTO = new Date(1996, 06, 13);
    Usuario usuario;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;
    @MockBean
    private VeiculoService veiculoService;

    @Test
    public void UsuarioToDTO() {
        Usuario usuario = new Usuario(NOME,
                EMAIL,
                CPF,
                DATA_NASCIMENTO);

        BDDMockito.given(usuarioService.toDTO(Mockito.any(Usuario.class))).willReturn();

        UsuarioDTO usuarioDTO = usuarioService.toDTO(usuario);
        assertEquals(NOME, usuarioDTO.getNome());
        assertEquals(EMAIL, usuarioDTO.getEmail());
        assertEquals(CPF, usuarioDTO.getCpf());
        assertEquals(DATA_NASCIMENTO, usuarioDTO.getDataNascimento());
    }

    @Test
    public void findAllUsuarios() throws Exception {
        Usuario usuario = new Usuario(NOME,
                EMAIL,
                CPF,
                DATA_NASCIMENTO);

        List<Usuario> usuarios = List.of(usuario);

        List<UsuarioDTO> usuariosDTO = usuarios.stream().map(u -> usuarioService.toDTO(u)).collect(Collectors.toList());

        BDDMockito.given(usuarioService.findAllDTO()).willReturn(usuariosDTO);
    }

    @Test
    public void findUsuarioByCpfOrEmail() throws Exception {
    }
}
