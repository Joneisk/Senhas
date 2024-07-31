package sicoob.credivale.senhas.DataBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sicoob.credivale.senhas.DataBase.entities.Senha;


public interface senhaRepository extends JpaRepository<Senha, Long> {
}
