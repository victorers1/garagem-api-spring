package zup.garagem.dto;

import javax.validation.constraints.NotNull;

public class VeiculoRequestDTO {
    @NotNull(message = "marca é obrigatória")
    private final String marcaId;

    @NotNull(message = "modelo é obrigatório")
    private final String modeloId;

    @NotNull(message = "ano é obrigatório")
    private final String anoModelo;

    @NotNull(message = "usuario é obrigatório")
    private final Long usuarioId;

    public VeiculoRequestDTO(String marcaId, String modeloId, String anoModelo, Long usuarioId) {
        this.marcaId = marcaId;
        this.modeloId = modeloId;
        this.anoModelo = anoModelo;
        this.usuarioId = usuarioId;
    }

    public String getMarcaId() {
        return marcaId;
    }

    public String getModeloId() {
        return modeloId;
    }

    public String getAnoModelo() {
        return anoModelo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}
