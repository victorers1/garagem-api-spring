package zup.garagem.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class VeiculoRequestDTO {
    @NotNull(message = "marcaId é obrigatória")
    private String marcaId;

    @NotNull(message = "modeloId é obrigatório")
    private String modeloId;

    @NotNull(message = "AnoModelo é obrigatório")
    private String anoModelo;

    public VeiculoRequestDTO() {}

    public VeiculoRequestDTO(String marcaId, String modeloId, String anoModelo) {
        this.marcaId = marcaId;
        this.modeloId = modeloId;
        this.anoModelo = anoModelo;
    }
}
