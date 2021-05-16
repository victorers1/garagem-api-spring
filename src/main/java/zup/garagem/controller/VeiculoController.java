package zup.garagem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zup.garagem.client.FIPEClient;
import zup.garagem.dto.ErroDTO;
import zup.garagem.dto.ErroValidacaoDTO;
import zup.garagem.dto.VeiculoRequestDTO;
import zup.garagem.dto.VeiculoResponseDTO;
import zup.garagem.entity.Veiculo;
import zup.garagem.repository.VeiculoRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final FIPEClient fipeClient;

    public VeiculoController(VeiculoRepository veiculoRepository, FIPEClient fipeClient) {
        this.veiculoRepository = veiculoRepository;
        this.fipeClient = fipeClient;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listar() {
        var veiculosDTO = veiculoRepository
                .findAll()
                .stream()
                .map(Veiculo::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(veiculosDTO);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Validated @RequestBody VeiculoRequestDTO v, BindingResult result) {
        if (result.hasErrors()) {
            var erro = new ErroValidacaoDTO(result, "Erro ao cadastrar veículo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }

        var veiculoFipeDTO = fipeClient.getVeiculo(v.getMarcaId(), v.getModeloId(), v.getAnoModelo());
        
        if(!veiculoFipeDTO.validar()){
            var erroDTO = new ErroDTO("FIPE Request", "Não foi possível consultar detalhes do veículo");
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroDTO);
        }

        var novoVeiculo = veiculoFipeDTO.toVeiculo();
        novoVeiculo = veiculoRepository.save(novoVeiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }
}
