package zup.garagem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zup.garagem.entity.Usuario;
import zup.garagem.repository.UsuarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario findByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByCpf(email);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario u) {
        return usuarioRepository.save(u);
    }
}
