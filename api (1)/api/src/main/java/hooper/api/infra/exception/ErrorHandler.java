package hooper.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// essa amotação diz para o Spring que essa classe é para tratamento de erros

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404Error() {
        return ResponseEntity.notFound().build();
        /*
        @ExceptionHandler(EntityNotFoundException.class): Esta anotação é usada
        para indicar que o método a seguir deve ser executado quando uma exceção
        do tipo EntityNotFoundException for lançada durante a execução de algum
        método em seu controlador. Em outras palavras, quando ocorrer uma
        exceção desse tipo, este método será chamado para lidar com ela.
         */

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle400Error(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorData::new).toList());
        /*
        ResponseEntity.badRequest(): Isso cria uma resposta com um status HTTP "400 Bad Request".
        Isso significa que algo deu errado na solicitação que o cliente fez (por exemplo, no
        preenchimento do formulário). .body(...): Isso é o conteúdo da resposta, ou seja, o
        que a resposta vai "dizer" ao cliente.
        errors.stream(): Aqui, ele pega a lista de erros que aconteceram no preenchimento do
        formulário (a variável "errors") e a transforma em um "fluxo" (stream) de dados.
        Isso permite que o código faça algo com cada erro individualmente.
        .map(ValidationErrorData::new): O código está mapeando cada erro da lista para um
        objeto do tipo ValidationErrorData. Isso significa que ele está convertendo cada erro
        em um formato específico que é mais fácil de enviar como resposta.
        .toList(): Finalmente, todos esses objetos ValidationErrorData são colocados
        em uma lista.
         */
    }

    private record ValidationErrorData(String field, String message) {
        /*
    nosso record DTO tem dois params: field: Este campo armazena o nome do campo do formulário
    onde ocorreu o erro de validação.
    message: Este campo armazena a mensagem de erro que explica o que está errado com o campo.
     */
        public ValidationErrorData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
     /*
    Isso é um construtor especial da classe ValidationErrorData. Um construtor é um
    método que é chamado quando você cria um objeto dessa classe. Nesse caso, o
    construtor recebe um objeto FieldError como argumento.

    O objetivo desse construtor é criar um objeto ValidationErrorData a partir de um
    objeto FieldError. Ele faz isso obtendo o nome do campo com erro (error.getField())
    e a mensagem de erro associada a esse campo (error.getDefaultMessage()).

    Então, quando você chama esse construtor com um FieldError, ele cria um novo objeto
    ValidationErrorData com os campos field e message preenchidos com as informações
    do erro.

    Em resumo, a classe ValidationErrorData é usada para estruturar e representar os
    dados dos erros de validação do formulário, e o construtor permite criar objetos
    ValidationErrorData de maneira conveniente a partir de objetos FieldError.
    Isso torna mais fácil organizar e manipular os erros antes de enviá-los como
    parte da resposta HTTP.
             */
        }
    }
}
