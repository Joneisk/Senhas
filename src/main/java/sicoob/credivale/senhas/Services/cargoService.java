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
        if (carRepo.existsByNome(cargo.getNome())) {
            throw new IllegalArgumentException("Cargo já cadastro");
        }
        return carRepo.save(cargo);
    }
    public boolean existsByNome(String nome) {
        return carRepo.existsByNome(nome);
    }


    public Cargo getById(Long id) {
        return carRepo.findById(id).orElse(null);
    }


    public Long getIdByNome(String nome) {
        Cargo cargo = carRepo.findByNome(nome);
        return (cargo != null) ? cargo.getId() : null;
    }


    public Cargo updateCargo(Cargo cargo) {
        Cargo existingCargo = carRepo.findById(cargo.getId()).orElse(null);
        if (existingCargo == null) {
            throw new IllegalArgumentException("Cargo não encontrado");
        }
        existingCargo.setNome(cargo.getNome());
        existingCargo.setNivel(cargo.getNivel());
        return carRepo.save(existingCargo);
    }

    public boolean deleteById(Long id) {
        if (!carRepo.existsById(id)) {
            return false;
        }
        try {
            carRepo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Cargo> getAll() {
        return carRepo.findAll();
    }
}
