package zup.garagem.dto;


import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErroValidacaoDTO {
    private String mensagem;
    private List<ErroDTO> erros = new ArrayList<>();

    public ErroValidacaoDTO() {
    }

    public ErroValidacaoDTO(BindingResult resultadoValidacao, String msg) {
        this.mensagem = msg;

        for (var e : resultadoValidacao.getFieldErrors()) {
            erros.add(new ErroDTO(e.getField(), e.getDefaultMessage()));
        }
    }
}
