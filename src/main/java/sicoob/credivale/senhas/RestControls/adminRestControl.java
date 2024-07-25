package sicoob.credivale.senhas.RestControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sicoob.credivale.senhas.DataBase.entities.Cargo;
import sicoob.credivale.senhas.Services.cargoService;


@RestController
@RequestMapping(value="apis/admin/")
public class adminRestControl {
    @GetMapping(value="connection-test")
    public String connectionTest(){
        return "connected";
    }



    @Autowired
    private cargoService cargService;

    @PostMapping("/add-cargo")
    public ResponseEntity<Object> addCargo (@RequestBody Cargo cargo) {
            if(cargService.existsByNome(cargo.getNome())){
                return ResponseEntity.badRequest().body("Cargo já cadastrado.");
            }
        return new ResponseEntity<>(cargService.addCargo(cargo), HttpStatus.OK);
    }

    @GetMapping("/get-cargo")
    public ResponseEntity<Object> getCargo (@RequestParam(value="nome") String nome) {
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
        return new ResponseEntity<>(cargService.getAll(),HttpStatus.OK);
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


}





