package zup.garagem.dto;

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

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAnoModelo() {
        return anoModelo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getDiaRodizioNome() {
        return diaRodizioNome;
    }

    public Boolean getRodizioAtivo() {
        return rodizioAtivo;
    }
}
