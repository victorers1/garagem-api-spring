package zup.garagem.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zup.garagem.service.UsuarioService;
import zup.garagem.service.VeiculoService;
import zup.garagem.dto.ErrosValidacaoDTO;
import zup.garagem.dto.UsuarioDTO;
import zup.garagem.dto.VeiculoResponseDTO;
import zup.garagem.repository.UsuarioRepository;
import zup.garagem.repository.VeiculoRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {
    private final UsuarioService usuarioService;
    private final VeiculoService veiculoService;

    public UsuarioRestController(UsuarioService usuarioService, VeiculoService veiculoService) {
        this.usuarioService = usuarioService;
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        var usuariosDTO =  usuarioService.findAllDTO();
        return ResponseEntity.ok(usuariosDTO);
    }

    @PostMapping
    public ResponseEntity<?> criar(@Validated @RequestBody UsuarioDTO u, BindingResult result) {
        if (result.hasErrors()) {
            ErrosValidacaoDTO erro = new ErrosValidacaoDTO(result, HttpStatus.BAD_REQUEST, "Erro ao validar usuário", "/usuarios");
            return ResponseEntity.badRequest().body(erro);
        }

        var novoUsuarioDTO = usuarioService.salvar(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuarioDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id) {
        var usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuarioService.toDTO(usuario));
    }

    @GetMapping("/{id}/veiculos")
    public ResponseEntity<?> listarVeiculosDoUsuario(@PathVariable Long id) {
        // findById() emite erro se usuário não existe
        usuarioService.findById(id);
        List<VeiculoResponseDTO> veiculosDTO = veiculoService.findAllDTOByUsuarioDonoId(id);
        return ResponseEntity.ok(veiculosDTO);
    }
}
