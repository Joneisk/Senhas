package sicoob.credivale.senhas.DataBase.repositories;


import sicoob.credivale.senhas.DataBase.entities.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface atendenteRepository extends JpaRepository<Atendente, Long> {

    /*
    boolean existsByLogin(String Cpf);
    FisicalPerson findByCpf(String Cpf);
    void deleteByCpf(String cpf);*/

    Atendente findByEmail(String email);
    boolean existsByEmail(String email);


}
