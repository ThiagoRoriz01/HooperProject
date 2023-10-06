package hooper.api.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Estamos nessa subpasta de contollers, para informar o Spring que é um controller usamos
essa anotação: @RestController essa anotação é para API Rest
 */
@RestController
/*
@RequestMapping é uma anotação que indica qual URL esse controller vai responder, no caso
se for passado /hello vai cair nesse controller
 */
@RequestMapping("/hello")
public class HelloController {

    /*
   O nosso controller possui essa função olaMundo que criamos. Quando for feita uma requisição no
   navegador para /hello a anotação @GetMapping indica que é uma requisição do tipo GET e essa
   função que criamos devolve o Hello World!
     */
    @GetMapping
    public String olaMundo() {
        return "What's up, Spring!";
    }

}

// RESUMO DA ALURA O QUE EU APRENDI:

/* Criar um projeto Spring Boot utilizando o site do Spring Initializr;
Importar o projeto no IntelliJ e executar uma aplicação Spring Boot pela classe contendo o método main;
Criar uma classe Controller e mapear uma URL nela utilizando as anotações @RestController e @RequestMapping;
Realizar uma requisição de teste no browser acessando a URL mapeada no Controller.

 */
