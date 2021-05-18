package zup.garagem.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zup.garagem.controller.UsuarioController;
import zup.garagem.controller.VeiculoController;
import zup.garagem.dto.ErroDTO;
import zup.garagem.dto.ListaErrosDTO;
import zup.garagem.dto.UsuarioDTO;
import zup.garagem.dto.VeiculoResponseDTO;
import zup.garagem.entity.Usuario;
import zup.garagem.entity.Veiculo;
import zup.garagem.repository.UsuarioRepository;
import zup.garagem.repository.VeiculoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioController usuarioController;
    private final VeiculoRepository veiculoRepository;
    private final VeiculoController veiculoController;

    public UsuarioRestController(UsuarioRepository usuarioRepository,
                                 VeiculoRepository veiculoRepository,
                                 UsuarioController usuarioController,
                                 VeiculoController veiculoController) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioController = usuarioController;
        this.veiculoRepository = veiculoRepository;
        this.veiculoController = veiculoController;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        var usuariosDTO = usuarioRepository
                .findAll()
                .stream()
                .map(u->usuarioController.toDTO(u))
                .collect(Collectors.toList());

        return ResponseEntity.ok(usuariosDTO);
    }

    @PostMapping
    public ResponseEntity<?> criar(@Validated @RequestBody UsuarioDTO u, BindingResult result) {
        if (result.hasErrors()) {
            ListaErrosDTO erro = new ListaErrosDTO(result, "Erro ao validar usuário");
            return ResponseEntity.badRequest().body(erro);
        }

        var novoUsuario = u.toUsuario();
        novoUsuario = usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioController.toDTO(novoUsuario));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            var erro = new ErroDTO("Usuário", "Usuário não encontrado");
            return ResponseEntity.badRequest().body(erro);
        }

        return ResponseEntity.ok().body(usuarioController.toDTO(usuario.get()));
    }

    @GetMapping("/{id}/veiculos")
    public ResponseEntity<?> listarVeiculosDoUsuario(@PathVariable(value = "id") Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            var erro = new ErroDTO("Usuário", "Usuário não encontrado");
            return ResponseEntity.badRequest().body(erro);
        }

        List<Veiculo> veiculos = veiculoRepository.findAllByUsuarioDonoId(id);
        List<VeiculoResponseDTO> veiculosDTO = new ArrayList<>();
        for (var veiculo : veiculos) {
            veiculosDTO.add(veiculoController.toResponseDTO(veiculo));
        }

        return ResponseEntity.ok(veiculosDTO);
    }
}
