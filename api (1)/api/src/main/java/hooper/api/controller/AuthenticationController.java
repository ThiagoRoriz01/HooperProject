package hooper.api.controller;

import hooper.api.domain.hooper.user.AuthenticationData;
import hooper.api.domain.hooper.user.User;
import hooper.api.infra.security.JWTDataToken;
import hooper.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    /*
    @Autowired para injetar uma instância de AuthenticationManager em uma
    classe. Isso é comumente usado em aplicações Spring para fornecer uma
    instância de uma classe gerenciada pelo Spring (como um serviço, um
    repositório, etc.) para outra classe que depende dela.
     */
    private AuthenticationManager manager;
    /*
    Em termos simples, o AuthenticationManager verifica as credenciais de
    um usuário (como nome de usuário e senha) e determina se essas credenciais
    são válidas. Ele decide se o usuário deve ser autenticado (permitido a
    acessar recursos) ou não.
     */

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData data) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        /*
        Essa classe acima é um DTO do próprio Spring usado pra representar o login e senha
        E abaixo o método authenticate recebe o DTO do próprio Spring
         */
        var authentication = manager.authenticate(authenticationToken);
        /*
        O metodo manager.authenticate devolve um obj que representa um usuario
        autenticado no sistema
         */

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTDataToken(tokenJWT));
    }
}
