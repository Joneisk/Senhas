package sicoob.credivale.senhas.DataBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sicoob.credivale.senhas.DataBase.entities.Senha;

import java.math.BigInteger;
import java.util.List;


public interface senhaRepository extends JpaRepository<Senha, Long> {


    @Query("SELECT s FROM Senha s WHERE s.tipoAtendimento.id = :tipoAtendimentoId")
    List<Senha> findByTipoAtendimentoId(@Param("tipoAtendimentoId") Long tipoAtendimentoId);

    @Query("SELECT s FROM Senha s ORDER BY s.id DESC")
    List<Senha> findLastSenha();

    @Query("SELECT s FROM Senha s WHERE s.tipoAtendimento.id=:tipoAtendimentoId ORDER BY s.id DESC LIMIT 1")
    Senha findLastSenhaByTipoAtendimento(@Param("tipoAtendimentoId") Long tipoAtendimentoId);



    @Query("SELECT s FROM Senha s WHERE s.tipoAtendimento.id IN (:ids) AND s.statusSenha.id < 3 ORDER BY s.tipoAtendimento.id")
    List<Senha> findByTipoAtendimentoIdIn(@Param("ids") List<Long> ids);
}
