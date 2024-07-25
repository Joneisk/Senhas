package sicoob.credivale.senhas.Services;

import sicoob.credivale.senhas.DataBase.entities.Atendente;
import sicoob.credivale.senhas.DataBase.repositories.atendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class atendenteService {
    @Autowired
    private atendenteRepository atenRepo;

    public Atendente addAtendente(Atendente atendente)
    {
        return atenRepo.save(atendente);
    }


    public Atendente getById(Long id) {
        return atenRepo.findById(id).orElse(null);
    }

    public List<Atendente> getAll() {
        return atenRepo.findAll();
    }


}
