package zup.garagem.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FIPEItemDTO {
    @NotNull(message = "Nome é obrigatório")
    private String nome;
    @NotNull(message = "Código é obrigatório")
    private Long codigo;
}
