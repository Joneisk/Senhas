package sicoob.credivale.senhas.Services;

import sicoob.credivale.senhas.DataBase.entities.Atendente;
import sicoob.credivale.senhas.DataBase.entities.Cargo;
import sicoob.credivale.senhas.DataBase.repositories.atendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sicoob.credivale.senhas.DataBase.repositories.cargoRepository;

import java.util.List;

@Service
public class atendenteService {
    @Autowired
    private atendenteRepository atenRepo;

    public Atendente addAtendente(Atendente atendente) {
        if (atenRepo.existsByEmail(atendente.getEmail())) {
            throw new IllegalArgumentException("Atendente já cadastro");
        }
        return atenRepo.save(atendente);
    }

    public boolean existsByEmail(String email) {
        return atenRepo.existsByEmail(email);
    }


    public Atendente getById(Long id) {
        return atenRepo.findById(id).orElse(null);
    }


    public Long getIdByEmail(String email) {
        Atendente atendente = atenRepo.findByEmail(email);
        return (atendente != null) ? atendente.getId() : null;
    }

    public Atendente getByEmail(String email) {
        return atenRepo.findByEmail(email);
    }

    public Atendente updateAtendente(Atendente atendente) {
        Atendente existingAtendente = atenRepo.findById(atendente.getId()).orElse(null);
        if (existingAtendente == null) {
            throw new IllegalArgumentException("Atendente não encontrado");
        }
        return atenRepo.save(existingAtendente);
    }


    public boolean deleteById(Long id) {
        if (!atenRepo.existsById(id)) {
            return false;
        }
        try {
            atenRepo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Atendente> getAll() {
        return atenRepo.findAll();
    }
}
