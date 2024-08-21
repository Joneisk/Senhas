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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


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

        ///Senha
        @PostMapping("/add-senha")
        public ResponseEntity<Object> addSenha(@RequestBody Senha senha){
            StatusSenha status;
            status=statusService.getById(1L);
            Senha ultimaSenhaPorTipo = senService.getLastSenhaByTipo(senha.getTipoAtendimento().getId());
            int novoValor;
            if(ultimaSenhaPorTipo==null)
            {
                novoValor=1;
            }
            else
            {
                novoValor = ultimaSenhaPorTipo.getSenha()+1;
                LocalDate dataAtual = LocalDate.now();
                if(!ultimaSenhaPorTipo.getDataEmissao().equals(dataAtual))
                {
                    novoValor=1;
                }
            }
            senha.setSenha(novoValor);
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

            //Caso alguém tente finalizar uma senha que ainda não foi chamada.
            StatusSenha status=senha.getStatusSenha();
            if(status.getId()==1){
                return ResponseEntity.badRequest().body("A senha ainda não foi chamada, não é possivel finalizar.");
            }
            status= statusService.getById(3L);
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

    @PostMapping("/finaliza2-senha")
    public ResponseEntity<Object> finaliza2Senha(@RequestParam("id") Long id) {
        try {
            if(id==null)
            {
                return ResponseEntity.badRequest().body("Id não pode ser nulo.");
            }
            Senha senha=senService.getById(id);

            StatusSenha status=senha.getStatusSenha();
            if(status.getId()==3){
                return ResponseEntity.badRequest().body("Senha já foi finalizada.");
            }
            if(status.getId()==1){
                return ResponseEntity.badRequest().body("A senha ainda não foi chamada, não é possivel finalizar.");
            }
            status= statusService.getById(4L);
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



    /*
    @GetMapping("/get-all-senhas-by-seq-tip-atend")
    public ResponseEntity<List<Senha>> getAllByTipoAtendimentoId(@RequestParam("seqatendimento") String seqatendimento)
    {

        List<Senha> senhas = senService.getAllByTipoAtendimentoId(tipoAtendimentoId);
        if (senhas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(senhas);
    }*/
    @GetMapping("/get-all-senhas-by-seq-atend")
    public ResponseEntity<List<Senha>> getAllBySeqAtendimento(@RequestParam("seqatendimento") String seqatendimento) {
        List<Senha> senhas = senService.getAllBySeqAtendimento(seqatendimento);
        if (senhas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(senhas);
    }


}