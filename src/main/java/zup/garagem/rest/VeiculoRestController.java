package zup.garagem.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zup.garagem.client.FIPEClient;
import zup.garagem.controller.VeiculoController;
import zup.garagem.dto.ErroDTO;
import zup.garagem.dto.ListaErrosDTO;
import zup.garagem.dto.VeiculoRequestDTO;
import zup.garagem.dto.VeiculoResponseDTO;
import zup.garagem.repository.UsuarioRepository;
import zup.garagem.repository.VeiculoRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
public class VeiculoRestController {

    private final VeiculoRepository veiculoRepository;
    private final UsuarioRepository usuarioRepository;
    private final VeiculoController veiculoController;
    private final FIPEClient fipeClient;

    public VeiculoRestController(VeiculoRepository veiculoRepository, UsuarioRepository usuarioRepository, FIPEClient fipeClient, VeiculoController veiculoController) {
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.fipeClient = fipeClient;
        this.veiculoController = veiculoController;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listar() {
        var veiculosDTO = veiculoRepository
                .findAll()
                .stream()
                .map(v->veiculoController.toResponseDTO(v))
                .collect(Collectors.toList());

        return ResponseEntity.ok(veiculosDTO);
    }

    @PostMapping
    public ResponseEntity<?> criar(@Validated @RequestBody VeiculoRequestDTO v, BindingResult result) {
        if (result.hasErrors()) {
            var erro = new ListaErrosDTO(result, "Erro ao validar veículo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }

        var usuario = usuarioRepository.findById(v.getUsuarioId());

        if (usuario.isEmpty()) {
            var erro = new ErroDTO("usuarioId", "Usuario não existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }

        var veiculoFipeDTO = fipeClient.getVeiculo(v.getMarcaId(), v.getModeloId(), v.getAnoModelo());
        var novoVeiculo = veiculoController.toVeiculo(veiculoFipeDTO, usuario.get());
        novoVeiculo = veiculoRepository.save(novoVeiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoController.toResponseDTO(novoVeiculo));
    }
}
