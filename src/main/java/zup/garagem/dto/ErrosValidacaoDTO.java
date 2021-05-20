package zup.garagem.dto;


import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrosValidacaoDTO {
    private final String timestamp;
    private final Integer status;
    private final List<ErroDTO> errors = new ArrayList<>();
    private final String message;
    private final String path;

    public ErrosValidacaoDTO(BindingResult resultadoValidacao, HttpStatus status, String message, String path) {
        this.message = message;
        this.path = path;
        this.status = status.value();
        this.timestamp = new Date().toString();

        for (var e : resultadoValidacao.getFieldErrors()) {
            errors.add(new ErroDTO(e.getField(), e.getDefaultMessage()));
        }
    }

    public String getMensagem() {
        return message;
    }

    public List<ErroDTO> getErros() {
        return errors;
    }
}
