package zup.garagem.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class UsuarioTest {

    @Test
    public void newUsuario() {
        Usuario usuario = new Usuario();
        assertNull(usuario.getNome());
        assertNull(usuario.getEmail());
        assertNull(usuario.getCpf());
        assertNull(usuario.getDataNascimento());
        assertNull(usuario.getId());

        usuario = new Usuario("Victor",
                "victor@gmail.com",
                "08398328428",
                new Date(1996, 06, 13));

        assertNull(usuario.getId());
        assertEquals("Victor", usuario.getNome());
        assertEquals("victor@gmail.com", usuario.getEmail());
        assertEquals("08398328428", usuario.getCpf());
        assertEquals(new Date(1996, 06, 13), usuario.getDataNascimento());
    }
}
