package zup.garagem.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import zup.garagem.entity.Usuario;
import zup.garagem.entity.Veiculo;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class VeiculoRepositoryTest {
    private static final String NOME = "Victor";
    private static final String EMAIL = "victor@gmail.com";
    private static final String CPF = "08398328428";
    private static final Date DATA_NASCIMENTO = new Date(96, Calendar.JUNE, 13);
    private static String MARCA_ID = "59";
    private static String MODELO_ID = "5586";
    private static String ANO_MODELO = "2018-3";
    private static String VALOR = "20.000";
    private static DayOfWeek DIA_RODIZIO = DayOfWeek.MONDAY;
    @Autowired
    VeiculoRepository veiculoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    private Usuario usuario;
    private Veiculo veiculo;

    @BeforeEach
    public void ArrangeVariables() {
        usuario = new Usuario(
                NOME,
                EMAIL,
                CPF,
                DATA_NASCIMENTO
        );

        veiculo = new Veiculo(
                MARCA_ID,
                MODELO_ID,
                ANO_MODELO,
                VALOR,
                usuario,
                DIA_RODIZIO
        );
    }

    @Test
    public void insertVeiculo() {
        // Arrange
        Veiculo veiculo = new Veiculo(
                MARCA_ID,
                MODELO_ID,
                ANO_MODELO,
                VALOR,
                usuario,
                DIA_RODIZIO
        );

        // Act
        veiculoRepository.save(veiculo);
        Integer countVeiculo = veiculoRepository.findAll().size();
        Veiculo veiculoEncontrado = veiculoRepository.findAll().get(0);

        // Assert
        assertEquals(1, countVeiculo);
        assertEquals(veiculo, veiculoEncontrado);
    }

    @Test
    public void findAllByUsuarioDonoId() {
        // Arrange
        usuarioRepository.save(usuario);
        veiculoRepository.save(veiculo);

        // Act
        Integer countVeiculo = veiculoRepository.findAll().size();
        List<Veiculo> veiculosEncontrados = veiculoRepository.findAllByUsuarioDonoId(usuario.getId());
        Veiculo veiculoEncontrado = veiculosEncontrados.get(0);

        // Assert
        assertEquals(1, countVeiculo);
        assertEquals(veiculo, veiculoEncontrado);
    }
}
