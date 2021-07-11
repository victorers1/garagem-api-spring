package zup.garagem.entity;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VeiculoTest {
    private Long ID = 1L;
    private String MARCA_ID = "59";
    private String MODELO_ID = "5586";
    private String ANO_MODELO = "2018-3";
    private String VALOR = "20.000";
    private DayOfWeek DIA_RODIZIO = DayOfWeek.MONDAY;
    private Usuario USUARIO_DONO = new Usuario();

    @Test
    public void newEmptyVeiculo() {
        Veiculo veiculo = new Veiculo();
        assertNull(veiculo.getId());
        assertNull(veiculo.getMarca());
        assertNull(veiculo.getModelo());
        assertNull(veiculo.getAnoModelo());
        assertNull(veiculo.getValor());
        assertNull(veiculo.getUsuarioDono());
        assertNull(veiculo.getDiaRodizio());
    }

    @Test
    public void newVeiculo() {
        Veiculo veiculo = new Veiculo(
                MARCA_ID,
                MODELO_ID,
                ANO_MODELO,
                VALOR,
                USUARIO_DONO,
                DIA_RODIZIO
        );

        assertEquals(veiculo.getId(), ID);
        assertEquals(veiculo.getMarca(), MARCA_ID);
        assertEquals(veiculo.getModelo(), MODELO_ID);
        assertEquals(veiculo.getValor(), VALOR);
        assertEquals(veiculo.getUsuarioDono(), USUARIO_DONO);
        assertEquals(veiculo.getDiaRodizio(), DIA_RODIZIO);

    }
}
