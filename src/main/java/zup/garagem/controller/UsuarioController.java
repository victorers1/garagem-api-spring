package zup.garagem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zup.garagem.dto.UsuarioDTO;
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
