package sicoob.credivale.senhas.DataBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sicoob.credivale.senhas.DataBase.entities.Guiche;


public interface guicheRepository extends JpaRepository<Guiche, Long> {
}
