package zup.garagem.dto;

import lombok.Getter;

@Getter
public class ErroDTO {
    private String campo;
    private String mensagem;

    public ErroDTO(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public ErroDTO(){}
}
