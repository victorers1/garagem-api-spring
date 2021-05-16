package zup.garagem.dto;

import lombok.Getter;

@Getter
public class VeiculoResponseDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String AnoModelo;

    public VeiculoResponseDTO() {
    }

    public VeiculoResponseDTO(Long id, String marca, String modelo, String anoModelo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.AnoModelo = anoModelo;
    }
}
