package zup.garagem.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zup.garagem.client.FIPEClient;
import zup.garagem.dto.ErrosValidacaoDTO;
import zup.garagem.dto.VeiculoFIPEDTO;
import zup.garagem.dto.VeiculoRequestDTO;
import zup.garagem.dto.VeiculoResponseDTO;
import zup.garagem.service.UsuarioService;
import zup.garagem.service.VeiculoService;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoRestController {

    private final UsuarioService usuarioService;
    private final VeiculoService veiculoService;
    private final FIPEClient fipeClient;

    public VeiculoRestController(UsuarioService usuarioService, FIPEClient fipeClient, VeiculoService veiculoService) {
        this.usuarioService = usuarioService;
        this.fipeClient = fipeClient;
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listar() {
        var veiculosDTO = veiculoService.findAllDTO();
        return ResponseEntity.ok(veiculosDTO);
    }

    @PostMapping
    public ResponseEntity<?> criar(@Validated @RequestBody VeiculoRequestDTO v, BindingResult result) {
        if (result.hasErrors()) {
            var erro = new ErrosValidacaoDTO(result, HttpStatus.BAD_REQUEST, "Erro ao validar veículo", "/veiculos");
            return ResponseEntity.badRequest().body(erro);
        }

        var usuario = usuarioService.findById(v.getUsuarioId());

        VeiculoFIPEDTO  veiculoFipeDTO;
        try {
            veiculoFipeDTO = fipeClient.getVeiculo(v.getMarcaId(), v.getModeloId(), v.getAnoModelo());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado");
        }
        var novoVeiculoDTO = veiculoService.salvarVeiculoFIPE(veiculoFipeDTO, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculoDTO);
    }
}
