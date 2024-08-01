package sicoob.credivale.senhas.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sicoob.credivale.senhas.DataBase.entities.Cargo;

import sicoob.credivale.senhas.DataBase.entities.Senha;
import sicoob.credivale.senhas.DataBase.repositories.senhaRepository;

import java.util.List;

@Service
public class senhaService {

    @Autowired
    private senhaRepository senhaRepo;

    public Senha addSenha(Senha senha) {
        return senhaRepo.save(senha);
    }
    /*
    public boolean existsByNome(String nome) {
        return carRepo.existsByNome(nome);
    }*/


    public Senha getById(Long id) {
        return senhaRepo.findById(id).orElse(null);
    }

    public Senha getLastSenha() {
        List<Senha> senhas = senhaRepo.findLastSenha();
        if (senhas.isEmpty()) {
            return null;
        }
        return senhas.get(0);
    }

    /*
    public Long getIdByNome(String nome) {
        Cargo cargo = carRepo.findByNome(nome);
        return (cargo != null) ? cargo.getId() : null;
    }*/


    public Senha updateSenha(Senha senha) {
        Senha existingSenha = senhaRepo.findById(senha.getId()).orElse(null);
        if (existingSenha == null) {
            throw new IllegalArgumentException("Senha não encontrado");
        }
        return senhaRepo.save(existingSenha);
    }

    public boolean deleteById(Long id) {
        if (!senhaRepo.existsById(id)) {
            return false;
        }
        try {
            senhaRepo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Senha> getAll() {
        return senhaRepo.findAll();
    }

    public List<Senha> getAllByTipoAtendimentoId(Long tipoAtendimentoId) {
        return senhaRepo.findByTipoAtendimentoId(tipoAtendimentoId);
    }

}
