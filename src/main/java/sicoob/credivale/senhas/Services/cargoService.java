package sicoob.credivale.senhas.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sicoob.credivale.senhas.DataBase.entities.Cargo;
import sicoob.credivale.senhas.DataBase.repositories.cargoRepository;

import java.util.List;

@Service
public class cargoService {
    @Autowired
    private cargoRepository carRepo;

    public Cargo addCargo(Cargo cargo) {
        return carRepo.save(cargo);
    }

    public Cargo getById(Long id) {
        return carRepo.findById(id).orElse(null);
    }

    public List<Cargo> getAll() {
        return carRepo.findAll();
    }
}
