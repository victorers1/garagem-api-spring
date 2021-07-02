package zup.garagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zup.garagem.entity.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.cpf = :cpf OR u.email = :email")
    Optional<Usuario> findUsuarioByCpfOrEmail(String cpf, String email);

    List<Usuario> findAll();
}
