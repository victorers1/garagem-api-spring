package zup.garagem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import zup.garagem.dto.UsuarioDTO;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = GaragemApplication.class
)
// TestPropertySource help us configure the locations of properties files specific to our tests.
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class GaragemIntegrationTest {

    private static final Long ID = 1L;

    private static final String NOME = "Victor";
    private static final String EMAIL = "victor@gmail.com";
    private static final String CPF = "08398328428";
    private static final Date DATA_NASCIMENTO = new Date(96, Calendar.JUNE, 13);

    @Autowired
    private MockMvc mvc;

    @Test
    public void criarUsuarioValido() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/usuarios")
                .content(getJsonPayload(ID, NOME, EMAIL, CPF, DATA_NASCIMENTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.nome").value(NOME))
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.cpf").value(CPF))
                .andExpect(jsonPath("$.dataNascimento").value("13-06-1996"));
    }

    public String getJsonPayload(Long id, String name, String Email, String cpf, Date dataNascimento) throws JsonProcessingException {
        UsuarioDTO dto = new UsuarioDTO(id, name, Email, cpf, dataNascimento);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dto);
    }

}

/**
 * "id": 1,
 * "nome": "Maria",
 * "email": "maria@gmail.com",
 * "cpf": "88131464008",
 * "dataNascimento": "01-01-1970"
 */
