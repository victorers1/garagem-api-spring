package zup.garagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zup.garagem.entity.Veiculo;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
//    @Query("SELECT v FROM Veiculo v WHERE v.usuarioDono.id = :id")
    List<Veiculo> findAllByUsuarioDonoId(Long id);
}
