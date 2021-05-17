package zup.garagem.entity;

import lombok.Getter;
import org.springframework.context.annotation.EnableMBeanExport;
import zup.garagem.dto.VeiculoResponseDTO;

import javax.persistence.*;
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

    @ManyToOne @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioDono;

    public Veiculo(Long id, String marca, String modelo, String anoModelo, String valor, Usuario usuarioDono) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.valor = valor;
        this.usuarioDono = usuarioDono;
    }

    public Veiculo() {
    }

    public VeiculoResponseDTO toResponseDTO() {
        return new VeiculoResponseDTO(id, marca, modelo, anoModelo, usuarioDono.getId());
    }
}
