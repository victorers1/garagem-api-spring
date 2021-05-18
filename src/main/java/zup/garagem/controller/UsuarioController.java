package zup.garagem.controller;

import org.springframework.stereotype.Controller;
import zup.garagem.dto.UsuarioDTO;
import zup.garagem.entity.Usuario;

@Controller
public class UsuarioController {

    public UsuarioDTO toDTO(Usuario u) {
        return new UsuarioDTO(u.getId(), u.getNome(), u.getEmail(), u.getCpf(), u.getDataNascimento());
    }
}
