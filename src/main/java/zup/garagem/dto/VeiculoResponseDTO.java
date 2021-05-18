package zup.garagem.dto;

import lombok.Getter;


@Getter
public class VeiculoResponseDTO {
    private final Long id;
    private final String marca;
    private final String modelo;
    private final String anoModelo;
    private final Long usuarioId;
    private final String diaRodizioNome;
    private final Boolean rodizioAtivo;

    public VeiculoResponseDTO(Long id,
                              String marca,
                              String modelo,
                              String anoModelo,
                              Long usuarioId,
                              String diaRodizioNome,
                              Boolean rodizioAtivo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.usuarioId = usuarioId;
        this.diaRodizioNome = diaRodizioNome;
        this.rodizioAtivo = rodizioAtivo;
    }
}
