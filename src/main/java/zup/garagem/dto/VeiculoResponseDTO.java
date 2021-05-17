package zup.garagem.dto;

import lombok.Getter;

@Getter
public class VeiculoResponseDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String anoModelo;
    private Long usuarioId;

    public VeiculoResponseDTO() {
    }

    public VeiculoResponseDTO(Long id, String marca, String modelo, String anoModelo, Long usuarioId) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.usuarioId = usuarioId;
    }
}
