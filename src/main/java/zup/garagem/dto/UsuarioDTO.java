package zup.garagem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsuarioDTO {
    @NotNull(message = "Nome é obrigatório")
    @Length(min = 3, max = 100, message = "Nome deve ter de 3 a 100 caracteres")
    private final String nome;

    @NotNull(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private final String email;

    @NotNull(message = "CPF é obrigatório")
    @Length(min = 11, max = 11, message = "CPF deve ter exatamente 11 dígitos")
    private final String cpf;

    @JsonFormat(pattern="yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private LocalDate dataNascimento;
    private Long id;

    public UsuarioDTO(String nome, String email, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
}