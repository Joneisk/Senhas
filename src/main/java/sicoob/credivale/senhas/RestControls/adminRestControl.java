package sicoob.credivale.senhas.RestControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sicoob.credivale.senhas.DataBase.entities.Atendente;
import sicoob.credivale.senhas.DataBase.entities.Cargo;
import sicoob.credivale.senhas.Services.atendenteService;
import sicoob.credivale.senhas.Services.cargoService;

import java.util.Collections;


@RestController
@RequestMapping(value="apis/admin/")
public class adminRestControl {
    @GetMapping(value = "connection-test")
    public String connectionTest() {
        return "connected";
    }


    ///////////////////////////////////////////Cargo//////////////////////////////////////////////////
    @Autowired
    private cargoService cargService;

    @PostMapping("/add-cargo")
    public ResponseEntity<Cargo> addCargo(@RequestBody Cargo cargo) {
        if (cargService.existsByNome(cargo.getNome())) {
            return ResponseEntity.badRequest().body(null);
        }
        Cargo savedCargo = cargService.addCargo(cargo);
        return ResponseEntity.ok(savedCargo);
    }

    @GetMapping("/get-cargo")
    public ResponseEntity<Object> getCargo(@RequestParam(value = "nome") String nome) {
        Long id = cargService.getIdByNome(nome);
        if (id == null) {
            return new ResponseEntity<>("Cargo not found", HttpStatus.NOT_FOUND);
        } else {
            Cargo cargo = cargService.getById(id);
            if (cargo == null) {
                return new ResponseEntity<>("Cargo not found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(cargo, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/get-all-cargos")
    public ResponseEntity<Object> getAllCargos() {
        return new ResponseEntity<>(cargService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/delete-cargo")
    public ResponseEntity<String> deleteCargo(@RequestParam("nome") String nome) {
        Long id = cargService.getIdByNome(nome);
        if (id != null && cargService.deleteById(id)) {
            return ResponseEntity.ok("Cargo excluido com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Erro ao excluir Cargo!");
        }
    }

    @PostMapping("/update-cargo")
    public ResponseEntity<Object> updateCargo(@RequestBody Cargo cargo) {
        try {
            if (cargo == null) {
                return ResponseEntity.badRequest().body("Cargo não pode ser nulo.");
            }

            if (cargo.getNome() == null || cargo.getNome().isEmpty()) {
                return ResponseEntity.badRequest().body("Nome do cargo não pode ser nulo ou vazio.");
            }

            // Atualizar o cargo
            cargService.updateCargo(cargo);

            return ResponseEntity.ok("Cargo atualizado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao atualizar cargo: " + e.getMessage());
        }
    }

    ///////////////////////////////////////////////////////Atendente//////////////////////////////////////////

    @Autowired
    private atendenteService AtenService;


    @PostMapping("/add-atendente")
    public ResponseEntity<Object> addAtendente(@RequestBody Atendente atendente) {
        if (AtenService.existsByEmail(atendente.getEmail())) {
            return ResponseEntity.badRequest().body("Atendente já cadastrado.");
        }
        Cargo cargo = cargService.getById(atendente.getCargo().getId());
        if (cargo == null)
        {
            return ResponseEntity.badRequest().body("Cargo não cadastrado.");
        }
        atendente.setCargo(cargo);
        return new ResponseEntity<>(AtenService.addAtendente(atendente), HttpStatus.OK);
    }

    @PostMapping("/active-desactive-atendente")
    public ResponseEntity<Object> desactiveAtendente(@RequestParam(value = "email") String email) {
        try {
            if (email == null || email.isEmpty()) {
                return ResponseEntity.badRequest().body("Email não pode ser nulo ou vazio.");
            }

            Atendente novoAtendente = AtenService.getByEmail(email);
            if (novoAtendente == null) {
                return ResponseEntity.badRequest().body("Atendente não existe.");
            }

            if(novoAtendente.getStatus()==1){
                novoAtendente.setStatus(0);
            }
            else
            {
                novoAtendente.setStatus(1);
            }
            AtenService.updateAtendente(novoAtendente);
            return ResponseEntity.ok("Atendente atualizado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao atualizar cargo: " + e.getMessage());
        }
    }

        @PostMapping("/update-atendente")
        public ResponseEntity<Object> updateAtendente(@RequestBody Atendente atendente) {
            try {
                if (atendente.getEmail() == null || atendente.getEmail().isEmpty()) {
                    return ResponseEntity.badRequest().body("Email não pode ser nulo ou vazio.");
                }

                Atendente novoAtendente = AtenService.getByEmail(atendente.getEmail());
                if (novoAtendente == null) {
                    return ResponseEntity.badRequest().body("Atendente não existe.");
                }

                if (!atendente.getLogin().isEmpty()) {
                    novoAtendente.setLogin(atendente.getLogin());
                }
                if(!atendente.getCpf().isEmpty())
                {
                    novoAtendente.setCpf(atendente.getCpf());
                }
                if(!atendente.getNome().isEmpty())
                {
                    novoAtendente.setNome(atendente.getNome());
                }

                /*if(atendente.getStatus() == 1) { // ou if(atendente.getStatus() != false) se for boolean
                    novoAtendente.setStatus(atendente.getStatus());
                }*/
                novoAtendente.setStatus(1);
                if(!atendente.getSenha().isEmpty())
                {
                    novoAtendente.setSenha(atendente.getSenha());
                }
                if(!atendente.getUnidade().isEmpty()){
                    novoAtendente.setUnidade(atendente.getUnidade());
                }
                if(!atendente.getSeqatendimento().isEmpty())
                {
                    novoAtendente.setSeqatendimento(atendente.getSeqatendimento());
                }

                AtenService.updateAtendente(novoAtendente);
                return ResponseEntity.ok("Atendente atualizado com sucesso.");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Erro ao atualizar cargo: " + e.getMessage());
            }
        }

    @GetMapping("/get-atendente")
    public ResponseEntity<Object> getAtendente(@RequestParam(value = "email") String email) {
        Long id = AtenService.getIdByEmail(email);
        if (id == null) {
            return new ResponseEntity<>("Cargo not found", HttpStatus.NOT_FOUND);
        } else {
            Atendente atendente = AtenService.getById(id);
            if (atendente == null) {
                return new ResponseEntity<>("Atendente not found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(atendente, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/get-all-atendentes")
    public ResponseEntity<Object> getAllAtendentes() {
        return new ResponseEntity<>(AtenService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/delete-atendente")
    public ResponseEntity<String> deleteAtendente(@RequestParam("email") String email) {
        Long id = AtenService.getIdByEmail(email);
        if (id != null && AtenService.deleteById(id)) {
            return ResponseEntity.ok("Atendente excluido com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Erro ao excluir Atendente!");
        }
    }
}





