package zup.garagem.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class VeiculoRequestDTO {
    @NotNull(message = "marca é obrigatória")
    private String marcaId;

    @NotNull(message = "modelo é obrigatório")
    private String modeloId;

    @NotNull(message = "ano é obrigatório")
    private String anoModelo;

    @NotNull(message = "usuario é obrigatório")
    private Long usuarioId;

    public VeiculoRequestDTO(String marcaId, String modeloId, String anoModelo, Long usuarioId) {
        this.marcaId = marcaId;
        this.modeloId = modeloId;
        this.anoModelo = anoModelo;
        this.usuarioId = usuarioId;
    }
}
