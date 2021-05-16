package zup.garagem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zup.garagem.dto.ErroValidacaoDTO;
import zup.garagem.dto.UsuarioDTO;
import zup.garagem.entity.Usuario;
import zup.garagem.repository.UsuarioRepository;
import zup.garagem.service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(Usuario::toDTO)
                .collect(Collectors.toList());
    }

//    @GetMapping
//    public ResponseEntity<Usuario> findByCpf(@Validated @RequestBody String cpf) {
//        UsuarioDTO u = usuarioService.findByCpf(cpf);
//        return ResponseEntity.ok(new UsuarioDTO());
//    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Validated @RequestBody UsuarioDTO u, BindingResult result) {

        if (result.hasErrors()) {
            ErroValidacaoDTO erro = new ErroValidacaoDTO(result, "Erro ao cadastrar usuário");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }

        var novoUsuario = u.toUsuario();
        novoUsuario = usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario.toDTO());
    }
}
