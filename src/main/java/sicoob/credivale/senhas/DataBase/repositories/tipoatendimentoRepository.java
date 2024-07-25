package sicoob.credivale.senhas.DataBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sicoob.credivale.senhas.DataBase.entities.TipoAtendimento;


public interface tipoatendimentoRepository extends JpaRepository<TipoAtendimento, Long> {
}
