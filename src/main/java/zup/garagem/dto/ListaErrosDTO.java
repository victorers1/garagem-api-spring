package zup.garagem.dto;


import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class ListaErrosDTO {
    private final String mensagem;
    private List<ErroDTO> erros= new ArrayList<>();

    public ListaErrosDTO(BindingResult resultadoValidacao, String msg) {
        this.mensagem = msg;

        for (var e : resultadoValidacao.getFieldErrors()) {
            erros.add(new ErroDTO(e.getField(), e.getDefaultMessage()));
        }
    }

    public String getMensagem() {
        return mensagem;
    }

    public List<ErroDTO> getErros() {
        return erros;
    }
}
