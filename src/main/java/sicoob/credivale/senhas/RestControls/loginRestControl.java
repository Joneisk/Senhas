package sicoob.credivale.senhas.RestControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sicoob.credivale.senhas.DataBase.entities.Atendente;
import sicoob.credivale.senhas.Security.JWTTokenProvider;
import sicoob.credivale.senhas.Services.atendenteService;

@RestController
@RequestMapping(value="apis/login/")
public class loginRestControl {
    @GetMapping(value = "connection-test")
    public String connectionTest() {
        return "connected";
    }


    @Autowired
    private atendenteService AtenService;

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody Atendente user) {
        try {
            Atendente existingUser = AtenService.getByEmail(user.getEmail());
            if (existingUser == null) {
                return ResponseEntity.badRequest().body("Usuário não cadastrado");
            }
            if (existingUser.getSenha().equals(user.getSenha())) {
                // Gera o token JWT
                String token = JWTTokenProvider.getToken(existingUser.getEmail(), "" + existingUser.getCargo().getNivel());

                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.badRequest().body("Senha incorreta");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao autenticar usuário: " + e.getMessage());
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

}
