package zup.garagem.dto;

public class ErroDTO {
    private final String titulo;
    private final String mensagem;

    public ErroDTO(String titulo, String mensagem) {
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
