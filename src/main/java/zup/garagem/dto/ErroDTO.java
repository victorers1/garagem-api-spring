package zup.garagem.dto;

import lombok.Getter;

public class ErroDTO {
    private String campo;
    private String mensagem;

    public ErroDTO(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public ErroDTO(){}

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
