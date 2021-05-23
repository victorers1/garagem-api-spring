package zup.garagem.dto;

import jdk.jfr.Timestamp;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        var agora = ZonedDateTime.now();
        var formatador = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault());
        this.timestamp = formatador.format(agora);

        for (var e : resultadoValidacao.getFieldErrors()) {
            errors.add(new ErroDTO(e.getField(), e.getDefaultMessage()));
        }
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public List<ErroDTO> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
