package sicoob.credivale.senhas.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sicoob.credivale.senhas.DataBase.entities.TipoAtendimento;
import sicoob.credivale.senhas.DataBase.repositories.tipoAtendimentoRepository;

import java.util.List;

@Service
public class tipoAtendimentoService {

    @Autowired
    private tipoAtendimentoRepository tiporepo;

    public TipoAtendimento getById(Long id) {
        return tiporepo.findById(id).orElse(null);
    }

    public List<TipoAtendimento> getAll() {
        return tiporepo.findAll();
    }
}
