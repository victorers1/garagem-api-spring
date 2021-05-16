package zup.garagem.dto;

import lombok.Data;

import java.util.List;

@Data
public class ModeloCarroDTO {
    List<FIPEItemDTO> anos;
    List<FIPEItemDTO> modelos;
}
