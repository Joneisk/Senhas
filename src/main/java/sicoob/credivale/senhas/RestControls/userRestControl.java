package sicoob.credivale.senhas.RestControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sicoob.credivale.senhas.DataBase.entities.Atendente;
import sicoob.credivale.senhas.DataBase.entities.Cargo;
import sicoob.credivale.senhas.DataBase.entities.Senha;
import sicoob.credivale.senhas.DataBase.entities.StatusSenha;
import sicoob.credivale.senhas.Services.cargoService;
import sicoob.credivale.senhas.Services.senhaService;
import sicoob.credivale.senhas.Services.statusSenhaService;

import java.time.LocalTime;

@RestController
@RequestMapping(value="apis/user/")
public class    userRestControl {
    @GetMapping(value="connection-test")
    public String connectionTest(){
        return "connected";
    }


    @Autowired
    private senhaService senService;
    @Autowired
    private statusSenhaService statusService;


    @PostMapping("/add-senha")
    public ResponseEntity<Object> addSenha(@RequestBody Senha senha){
        StatusSenha status;
        status=statusService.getById(1L);
        senha.setNumeroChamadas(0);
        senha.setStatusSenha(status);
        return new ResponseEntity<>(senService.addSenha(senha), HttpStatus.OK);
    }

    @PostMapping("/chamar-senha-novamente")
    public ResponseEntity<Object> chamarSenhaNovamente(@RequestParam("id") Long id){
        try {
            if(id==null)
            {
                return ResponseEntity.badRequest().body("Id não pode ser nulo.");
            }
            Senha senha=senService.getById(id);
            int chamados=senha.getNumeroChamadas();
            chamados=chamados+1;
            senha.setNumeroChamadas(chamados);
            //Caso alguém tente chamar uma senha já finalizada
            StatusSenha status=senha.getStatusSenha();
            if(status.getId()==3){
                return ResponseEntity.badRequest().body("Senha já foi finalizada.");
            }

            senService.updateSenha(senha);
            return ResponseEntity.ok(senha);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao atualizar senha: " + e.getMessage());
        }
    }

    @PostMapping("/finaliza-senha")
    public ResponseEntity<Object> finalizaSenha(@RequestParam("id") Long id) {
        try {
            if(id==null)
            {
                return ResponseEntity.badRequest().body("Id não pode ser nulo.");
            }
            Senha senha=senService.getById(id);
            LocalTime horaAtual=LocalTime.now();
            senha.setHorafimatendimento(horaAtual);
            StatusSenha status= statusService.getById(3L);
            senha.setStatusSenha(status);
            senService.updateSenha(senha);
            return ResponseEntity.ok(senha);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao finalizar senha: " + e.getMessage());
        }
    }


    @PostMapping("/chamar-senha")
    public ResponseEntity<Object> chamarSenha(@RequestParam("id") Long id) {
        try {

            if(id==null)
            {
                return ResponseEntity.badRequest().body("Id não pode ser nulo.");
            }
            Senha senha=senService.getById(id);
            LocalTime horaAtual=LocalTime.now();
            senha.setHoraChamada(horaAtual);
            senha.setNumeroChamadas(1);
            StatusSenha status= statusService.getById(2L);
            senha.setStatusSenha(status);
            senService.updateSenha(senha);
            return ResponseEntity.ok(senha);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao atualizar senha: " + e.getMessage());
        }
    }

    @GetMapping("/get-all-senhas")
    public ResponseEntity<Object> getAllAtendentes() {
        return new ResponseEntity<>(senService.getAll(), HttpStatus.OK);
    }

}