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
    private String marca;

    @NotNull
    private String modelo;

    @NotNull
    private Integer ano;

    public Veiculo(Long id , String marca, String modelo, Integer ano) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public Veiculo() {}

    public VeiculoDTO toDTO(){
        return new VeiculoDTO(id, marca, modelo, ano);
    }
}
