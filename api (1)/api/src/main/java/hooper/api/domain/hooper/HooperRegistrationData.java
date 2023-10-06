package hooper.api.domain.hooper;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
esse é o record que contem cada atributo do corpo da requisição, o Java facilita com essa
funcionalidade, apenas precisamos declarar como está abaixo, os getters e setters estão embutidos
isso é chamado de DTO, a forma que usamos para receber e enviar dados da API usando um record
    que é uma classe Java.
 */
public record HooperRegistrationData(
        /* essas anotações são do bean validation. Elas são responsáveis por dizer por exemplo
        que um campo não pode ser vazio, ou que determinado campo é um email etc.
         */
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String phone,
        @NotBlank
        String courtPosition) {

    /*
    ESTOU COLOCANDO ESSE COMENTÁRIO AQUI PQ FUI ADICIONAR ELE NA MIGRATION DEPOIS DE JA TER CRIADO ELA
    E DEU ERRO, PROVAVELMENTE O COMENTÁRIO CONTA COMO ALTERAÇÃO
     as migrations servem para termos controle de versão do banco de dados, controlamos cada alteração
     */
}
