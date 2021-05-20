package zup.garagem.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;
import zup.garagem.dto.VeiculoFIPEDTO;
import zup.garagem.dto.VeiculoResponseDTO;
import zup.garagem.entity.Usuario;
import zup.garagem.entity.Veiculo;
import zup.garagem.repository.VeiculoRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class VeiculoService {

    VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public VeiculoResponseDTO salvarVeiculoFIPE(VeiculoFIPEDTO veiculoFipeDTO, Usuario dono) {
        var veiculo = toVeiculo(veiculoFipeDTO, dono);
        return toResponseDTO(veiculoRepository.save(veiculo));
    }

    public Veiculo findById(Long id) {
        var veiculo = veiculoRepository.findById(id);
        if (veiculo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veículo com não existe");
        }
        return veiculo.get();
    }

    public List<VeiculoResponseDTO> findAllDTO(){
        return veiculoRepository
                .findAll()
                .stream()
                .map(v -> toResponseDTO(v))
                .collect(Collectors.toList());
    }

    public List<VeiculoResponseDTO> findAllDTOByUsuarioDonoId(Long id){
        return mapToResponseDTO(veiculoRepository.findAllByUsuarioDonoId(id));
    }

    public VeiculoResponseDTO toResponseDTO(Veiculo v) {
        DayOfWeek diaRodizio = calcDiaRodizio(v.getAnoModelo());
        return new VeiculoResponseDTO(
                v.getId(),
                v.getMarca(),
                v.getModelo(),
                v.getAnoModelo(),
                v.getUsuarioDono().getId(),
                diaRodizio.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                calcRodizioAtivo(diaRodizio)
        );
    }

    public List<VeiculoResponseDTO> mapToResponseDTO(List<Veiculo> veiculos) {
        List<VeiculoResponseDTO> veiculosDTO = new ArrayList<>();
        for (var veiculo : veiculos) {
            veiculosDTO.add(toResponseDTO(veiculo));
        }

        return veiculosDTO;
    }

    public Veiculo toVeiculo(VeiculoFIPEDTO v, Usuario u) throws RuntimeException {
        DayOfWeek diaRodizio = calcDiaRodizio(v.getAnoModelo());
        return new Veiculo(v.getId(), v.getMarca(), v.getModelo(), v.getAnoModelo(), v.getValor(), u, diaRodizio);
    }

    Boolean calcRodizioAtivo(DayOfWeek dia) {
        LocalDate hoje = LocalDate.now();
        return hoje.getDayOfWeek() == dia;
    }

    DayOfWeek calcDiaRodizio(String ano) throws RuntimeException {
        char ultimoDigito = ano.charAt(ano.length() - 1);
        switch (ultimoDigito) {
            case '0':
            case '1':
                return DayOfWeek.MONDAY;
            case '2':
            case '3':
                return DayOfWeek.TUESDAY;
            case '4':
            case '5':
                return DayOfWeek.WEDNESDAY;
            case '6':
            case '7':
                return DayOfWeek.THURSDAY;
            case '8':
            case '9':
                return DayOfWeek.FRIDAY;
            default:
                throw new RuntimeException();
        }
    }
}