package sicoob.credivale.senhas.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import sicoob.credivale.senhas.DataBase.entities.Senha;
import sicoob.credivale.senhas.DataBase.repositories.senhaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Service
public class senhaService {

    @Autowired
    private senhaRepository senhaRepo;

    public Senha addSenha(Senha senha) {
        return senhaRepo.save(senha);
    }

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

    public Senha getLastSenhaByTipo(Long tipoAtendimentoId) {
        return senhaRepo.findLastSenhaByTipoAtendimento(tipoAtendimentoId);
    }

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

    public List<Senha> getAllBySeqAtendimento(String seqatendimento) {
        String[] seqatendimentoSplit = seqatendimento.split("");
        List<Long> ids = Arrays.stream(seqatendimentoSplit)
                .map(Long::valueOf)
                .collect(Collectors.toList());
        return senhaRepo.findByTipoAtendimentoIdIn(ids);
    }

    public List<Senha> getAllByTipoAtendimentoId(Long tipoAtendimentoId) {
        return senhaRepo.findByTipoAtendimentoId(tipoAtendimentoId);
    }

}
