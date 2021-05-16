package zup.garagem.dto;

import lombok.Getter;
import zup.garagem.entity.Veiculo;

import javax.validation.constraints.NotNull;

@Getter
public class VeiculoDTO {
    private Long id;

    @NotNull(message = "Marca é obrigatória")
    private String marca;

    @NotNull(message = "Modelo é obrigatório")
    private String modelo;

    @NotNull(message = "Ano é obrigatório")
    private Integer ano;

    public VeiculoDTO() {
    }

    public VeiculoDTO(Long id, String marca, String modelo, Integer ano) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public Veiculo toVeiculo() {
        return new Veiculo(id, marca, modelo, ano);
    }
}
