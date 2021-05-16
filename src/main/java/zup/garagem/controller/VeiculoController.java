package zup.garagem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zup.garagem.client.FIPEClient;
import zup.garagem.dto.ErroValidacaoDTO;
import zup.garagem.dto.VeiculoDTO;
import zup.garagem.repository.VeiculoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final FIPEClient fipeClient;

    public VeiculoController(VeiculoRepository veiculoRepository, FIPEClient fipeClient){
        this.veiculoRepository = veiculoRepository;
        this.fipeClient = fipeClient;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> listar(){
        var veiculosDTO = veiculoRepository
                .findAll()
                .stream()
                .map(v->v.toDTO())
                .collect(Collectors.toList());

        return ResponseEntity.ok(veiculosDTO);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Validated @RequestBody VeiculoDTO v, BindingResult result){
        if (result.hasErrors()) {
            ErroValidacaoDTO erro = new ErroValidacaoDTO(result, "Erro ao cadastrar veículo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }

        var novoVeiculo = v.toVeiculo();
//        var marca =
        novoVeiculo = veiculoRepository.save(novoVeiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo.toDTO());
    }
}
