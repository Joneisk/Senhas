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


    /*
    public Atendente addUser(Atendente user) {
        if (atenRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Login already exists");
        }
        return userRepo.save(user);
    }

    public User updateUser(User user) {
        User existingUser = userRepo.findById(user.getId()).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (existingUser.hasFullAccess() && !user.isActive() && userRepo.countByFullAccessAndActive() <= 1) {
            throw new IllegalArgumentException("Cannot deactivate the last user with full access");
        }
        return userRepo.save(user);
    }


    public boolean deleteById(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return false;
        }
        if (user.hasFullAccess() && userRepo.countByFullAccessAndActive() <= 1) {
            throw new IllegalArgumentException("Cannot delete the last user with full access");
        }
        try {
            userRepo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }*/

    public Atendente getById(Long id) {
        return atenRepo.findById(id).orElse(null);
    }

    /*
    public Atendente getByEmail(String email) {
        return atenRepo.findByEmail(email);
    }*/

    public List<Atendente> getAll() {
        return atenRepo.findAll();
    }
}
