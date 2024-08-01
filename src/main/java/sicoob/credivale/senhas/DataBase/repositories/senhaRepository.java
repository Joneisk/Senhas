package sicoob.credivale.senhas.DataBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sicoob.credivale.senhas.DataBase.entities.Senha;

import java.util.List;


public interface senhaRepository extends JpaRepository<Senha, Long> {


    @Query("SELECT s FROM Senha s WHERE s.tipoAtendimento.id = :tipoAtendimentoId")
    List<Senha> findByTipoAtendimentoId(@Param("tipoAtendimentoId") Long tipoAtendimentoId);

    @Query("SELECT s FROM Senha s ORDER BY s.id DESC")
    List<Senha> findLastSenha();
}
