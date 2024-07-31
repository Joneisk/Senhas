package sicoob.credivale.senhas.DataBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sicoob.credivale.senhas.DataBase.entities.StatusSenha;


public interface statusSenhaRepository extends JpaRepository<StatusSenha, Long> {
}
