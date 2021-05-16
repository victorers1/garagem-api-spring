package zup.garagem.dto;

import lombok.Getter;
import zup.garagem.entity.Veiculo;

import javax.validation.constraints.NotNull;

@Getter
public class VeiculoRequestDTO {
    @NotNull(message = "Marca é obrigatória")
    private Long marcaId;

    @NotNull(message = "Modelo é obrigatório")
    private Long modeloId;

    @NotNull(message = "Ano é obrigatório")
    private String AnoModelo;

    public VeiculoRequestDTO() {}

    public VeiculoRequestDTO(Long marcaId, Long modeloId, String anoModelo) {
        this.marcaId = marcaId;
        this.modeloId = modeloId;
        this.AnoModelo = anoModelo;
    }
}
