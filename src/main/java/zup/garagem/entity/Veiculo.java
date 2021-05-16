package zup.garagem.entity;

import lombok.Getter;
import zup.garagem.dto.VeiculoDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long marcaId;
    private String marca;

    @NotNull
    private Long modeloId;
    private String modelo;

    @NotNull
    private Integer ano;

    private String valor;

    public Veiculo(Long id, Long marcaId, String marca, Long modeloId, String modelo, Integer ano, String valor) {
        this.id = id;
        this.marcaId = marcaId;
        this.marca = marca;
        this.modeloId = modeloId;
        this.modelo = modelo;
        this.ano = ano;
        this.valor = valor;
    }

    public Veiculo() {
    }

    public VeiculoDTO toDTO() {
        return new VeiculoDTO(id, marcaId, marca, modeloId, modelo, ano, valor);
    }
}
