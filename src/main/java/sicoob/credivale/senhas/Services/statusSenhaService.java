package sicoob.credivale.senhas.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sicoob.credivale.senhas.DataBase.entities.StatusSenha;
import sicoob.credivale.senhas.DataBase.repositories.statusSenhaRepository;

import java.util.List;
@Service
public class statusSenhaService {

    @Autowired
    private statusSenhaRepository statusRepo;

    /*
    public StatusSenha addStatus(StatusSenha status) {
        return statusRepo.save(status);
    }*/

    public StatusSenha getById(Long id) {
        return statusRepo.findById(id).orElse(null);
    }


  /*
    public StatusSenha updateStatus(StatusSenha status) {
        return statusRepo.save(status);
    }

    public boolean deleteById(Long id) {
        if (!statusRepo.existsById(id)) {
            return false;
        }
        try {
            statusRepo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    */
    public List<StatusSenha> getAll() {
        return statusRepo.findAll();
    }
}
