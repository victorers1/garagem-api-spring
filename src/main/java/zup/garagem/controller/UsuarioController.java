package zup.garagem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zup.garagem.entity.Usuario;
import zup.garagem.service.UsuarioService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.findAll();
    }

    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario u) {
        return usuarioService.save(u);
    }
}
