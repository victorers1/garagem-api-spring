package zup.garagem.dto;

import lombok.Getter;
import zup.garagem.entity.Usuario;
import zup.garagem.entity.Veiculo;

import javax.validation.constraints.NotNull;

@Getter
public class VeiculoFIPEDTO {
    private final Long id;

    @NotNull(message = "Marca é obrigatória")
    private final String Marca;

    @NotNull(message = "Modelo é obrigatório")
    private final String Modelo;

    @NotNull(message = "AnoModelo é obrigatório")
    private final String AnoModelo;

    private final String Valor;
    private final String Combustivel;
    private final String CodigoFipe;
    private final String MesReferencia;
    private final String TipoVeiculo;
    private final String SiglaCombustivel;

    public VeiculoFIPEDTO(Long id,
                          String Marca,
                          String Modelo,
                          String AnoModelo,
                          String Valor,
                          String Combustivel,
                          String CodigoFipe,
                          String MesReferencia,
                          String TipoVeiculo,
                          String SiglaCombustivel) {

        this.id = id;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.AnoModelo = AnoModelo;
        this.Valor = Valor;
        this.Combustivel = Combustivel;
        this.CodigoFipe = CodigoFipe;
        this.MesReferencia = MesReferencia;
        this.TipoVeiculo = TipoVeiculo;
        this.SiglaCombustivel = SiglaCombustivel;
    }

    public Veiculo toVeiculo(Usuario usuario) {
        return new Veiculo(id, Marca, Modelo, AnoModelo, Valor, usuario);
    }
}
