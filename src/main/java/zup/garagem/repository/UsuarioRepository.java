package zup.garagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zup.garagem.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select u from Usuario u where u.cpf = :cpf")
    Usuario findByCpf(@Param("cpf") String cpf);

    @Query("select u from Usuario u where u.email = :email")
    Usuario findByEmail(@Param("email") String email);
}
