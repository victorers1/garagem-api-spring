package zup.garagem.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import zup.garagem.dto.UsuarioDTO;
import zup.garagem.entity.Usuario;
import zup.garagem.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        if (existe(usuarioDTO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um usuário com este CPF ou e-mail");
        }
        return toDTO(usuarioRepository.save(toUsuario(usuarioDTO)));
    }

    public List<UsuarioDTO> findAllDTO() {
        return usuarioRepository.findAll()
                .stream()
                .map(u -> toDTO(u))
                .collect(Collectors.toList());
    }

    public Usuario findById(Long id) throws ResponseStatusException {
        Optional<Usuario> u = usuarioRepository.findById(id);
        if (u.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não existe");
        }
        return u.get();
    }

    public Boolean existe(UsuarioDTO u) {
        return usuarioRepository.findUsuarioByCpfOrEmail(u.getCpf(), u.getEmail()).isPresent();
    }

    public Usuario toUsuario(UsuarioDTO u) {
        return new Usuario(u.getNome(), u.getEmail(), u.getCpf(), u.getDataNascimento());
    }

    public UsuarioDTO toDTO(Usuario u) {
        return new UsuarioDTO(u.getId(), u.getNome(), u.getEmail(), u.getCpf(), u.getDataNascimento());
    }

}
