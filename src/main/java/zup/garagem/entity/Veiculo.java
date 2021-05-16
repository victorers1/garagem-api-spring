package zup.garagem.entity;

import lombok.Getter;
import zup.garagem.dto.VeiculoResponseDTO;

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
    private String anoModelo;

    private String valor;

    public Veiculo(Long id, String marca, String modelo, String anoModelo, String valor) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.valor = valor;
    }

    public Veiculo() {
    }

    public VeiculoResponseDTO toResponseDTO() {
        return new VeiculoResponseDTO(id, marca, modelo, anoModelo);
    }
}
