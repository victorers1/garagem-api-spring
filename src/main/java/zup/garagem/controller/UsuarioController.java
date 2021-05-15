package zup.garagem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zup.garagem.dto.UsuarioDTO;
import zup.garagem.entity.Usuario;
import zup.garagem.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService1) {
        this.usuarioService = usuarioService1;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.findAll();
    }

//    @GetMapping
//    public ResponseEntity<Usuario> findByCpf(@Validated @RequestBody String cpf) {
//        UsuarioDTO u = usuarioService.findByCpf(cpf);
//        return ResponseEntity.ok(new UsuarioDTO());
//    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@Validated @RequestBody UsuarioDTO u) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(u));
    }
}
