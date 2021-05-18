package zup.garagem.controller;

import org.springframework.stereotype.Controller;
import zup.garagem.dto.VeiculoFIPEDTO;
import zup.garagem.dto.VeiculoResponseDTO;
import zup.garagem.entity.Usuario;
import zup.garagem.entity.Veiculo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Controller
public class VeiculoController {

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

    public Veiculo toVeiculo(VeiculoFIPEDTO v, Usuario u) {
        DayOfWeek diaRodizio = calcDiaRodizio(v.getAnoModelo());
        return new Veiculo(v.getId(), v.getMarca(), v.getModelo(), v.getAnoModelo(), v.getValor(), u, diaRodizio);
    }

    Boolean calcRodizioAtivo(DayOfWeek dia){
        LocalDate hoje = LocalDate.now();
        return hoje.getDayOfWeek() == dia;
    }

    DayOfWeek calcDiaRodizio(String ano) {
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