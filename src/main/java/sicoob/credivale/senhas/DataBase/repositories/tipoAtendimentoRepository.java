package sicoob.credivale.senhas.DataBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sicoob.credivale.senhas.DataBase.entities.TipoAtendimento;


public interface tipoAtendimentoRepository extends JpaRepository<TipoAtendimento, Long> {
}
