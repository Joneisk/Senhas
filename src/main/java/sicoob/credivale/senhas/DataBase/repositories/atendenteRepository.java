package sicoob.credivale.senhas.DataBase.repositories;


import sicoob.credivale.senhas.DataBase.entities.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface atendenteRepository extends JpaRepository<Atendente, Long> {

    /*
    boolean existsByCpf(String Cpf);
    FisicalPerson findByCpf(String Cpf);
    void deleteByCpf(String cpf);*/

}
