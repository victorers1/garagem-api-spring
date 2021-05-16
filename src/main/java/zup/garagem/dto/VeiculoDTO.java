package zup.garagem.dto;

import lombok.Getter;
import zup.garagem.entity.Veiculo;

import javax.validation.constraints.NotNull;

@Getter
public class VeiculoDTO {
    private Long id;

    @NotNull(message = "Marca é obrigatória")
    private Long marcaId;
    private String marca;

    @NotNull(message = "Modelo é obrigatório")
    private Long modeloId;
    private String modelo;

    @NotNull(message = "Ano é obrigatório")
    private Integer anoModelo;

    private String valor;
    private String combustivel;
    private String codigoFipe;
    private String mesReferencia;
    private String tipoVeiculo;
    private String siglaCombustivel;

    public VeiculoDTO() {
    }

    public VeiculoDTO(Long id, Long marcaId, String marca, Long modeloId, String modelo, Integer ano, String valor) {
        this.id = id;
        this.marcaId = marcaId;
        this.marca = marca;
        this.modeloId = modeloId;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.valor = valor;
    }

    public VeiculoDTO(Long id,
                      Long marcaId,
                      String marca,
                      Long modeloId,
                      String modelo,
                      Integer anoModelo,
                      String valor,
                      String combustivel,
                      String codigoFipe,
                      String mesReferencia,
                      String tipoVeiculo,
                      String siglaCombustivel) {
        this.id = id;
        this.marcaId = marcaId;
        this.marca = marca;
        this.modeloId = modeloId;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.valor = valor;
        this.combustivel = combustivel;
        this.codigoFipe = codigoFipe;
        this.mesReferencia = mesReferencia;
        this.tipoVeiculo = tipoVeiculo;
        this.siglaCombustivel = siglaCombustivel;
    }

    public Veiculo toVeiculo() {
        return new Veiculo(id, marcaId, marca, modeloId, modelo, anoModelo, valor);
    }
}
