package sicoob.credivale.senhas.DataBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sicoob.credivale.senhas.DataBase.entities.Cargo;

public interface cargoRepository extends JpaRepository<Cargo, Long> {


    boolean existsByNome(String nome);
    Cargo findByNome(String nome);


}
