package zup.garagem.dto;

import lombok.Getter;
import zup.garagem.entity.Veiculo;

import javax.validation.constraints.NotNull;

@Getter
public class VeiculoFIPEDTO {
    private Long id;

    @NotNull(message = "Marca é obrigatória")
    private String Marca;

    @NotNull(message = "Modelo é obrigatório")
    private String Modelo;

    @NotNull(message = "AnoModelo é obrigatório")
    private String AnoModelo;

    private String Valor;
    private String Combustivel;
    private String CodigoFipe;
    private String MesReferencia;
    private String TipoVeiculo;
    private String SiglaCombustivel;

    public VeiculoFIPEDTO() {}

    public VeiculoFIPEDTO(Long id, String marca, String modelo, String anoModelo, String valor, String combustivel, String codigoFipe, String mesReferencia, String tipoVeiculo, String siglaCombustivel) {
        this.id = id;
        this.Marca = marca;
        this.Modelo = modelo;
        this.AnoModelo = anoModelo;
        this.Valor = valor;
        this.Combustivel = combustivel;
        this.CodigoFipe = codigoFipe;
        this.MesReferencia = mesReferencia;
        this.TipoVeiculo = tipoVeiculo;
        this.SiglaCombustivel = siglaCombustivel;
    }

    public Veiculo toVeiculo() {
        return new Veiculo(id, Marca, Modelo, AnoModelo, Valor);
    }
}
