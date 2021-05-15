package zup.garagem.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import zup.garagem.dto.UsuarioDTO;
import zup.garagem.entity.Usuario;
import zup.garagem.repository.UsuarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    ModelMapper modelMapper = new ModelMapper();

    public Usuario findByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = modelMapper.map(usuarioDTO, Usuario.class);
        return modelMapper.map(usuarioRepository.save(novoUsuario), UsuarioDTO.class);
    }
}
