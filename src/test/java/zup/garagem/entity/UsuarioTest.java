package zup.garagem.entity;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UsuarioTest {

    private static final String NOME = "Victor";
    private static final String EMAIL = "victor@gmail.com";
    private static final String CPF = "08398328428";
    private static final Date DATA_NASCIMENTO = new Date(96, Calendar.JUNE, 13);

    @Test
    public void newEmptyUsuario() {
        Usuario usuario = new Usuario();
        assertNull(usuario.getId());
        assertNull(usuario.getNome());
        assertNull(usuario.getEmail());
        assertNull(usuario.getCpf());
        assertNull(usuario.getDataNascimento());

        usuario = new Usuario(
                NOME,
                EMAIL,
                CPF,
                DATA_NASCIMENTO
        );

        assertNull(usuario.getId());
        assertEquals(NOME, usuario.getNome());
        assertEquals(EMAIL, usuario.getEmail());
        assertEquals(CPF, usuario.getCpf());
        assertEquals(DATA_NASCIMENTO, usuario.getDataNascimento());
    }
}
