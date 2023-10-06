package hooper.api.domain.hooper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


//essa anotação diz o nome da nossa tabela no banco de dados
@Table(name = "Hoopers")
//essa anotação diz o nome da nossa entidade JPA que representa a tabela no banco de daods
@Entity(name = "HooperJPAEntity")

/*
essas anotações abaixo abstraem a verbosidade como getter, construtores e etc.
Para fazer uso delas estamos usando a dependência lombok.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

/*
essa é uma classe Java que é uma entidade JPA, que tem a responsabilidade de representar uma
tabela no banco de dados. A entidade JPA possui os mesmos atributos do nosso record
HooperRegistrationData.
 */
public class HooperJPAEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String courtPosition;
    private Boolean ativo;


    public HooperJPAEntity(HooperRegistrationData data) {
        this.ativo = true;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.courtPosition = data.courtPosition();
    }

    public void updateInformation(HooperDataUpdate data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.courtPosition() != null) {
            this.courtPosition = data.courtPosition();
        }
        /*
        pq colocamos essas condições?
        aqui fazemos a atualização dos dados cadastrados no BD pelo que vem no DTO
        se determinado campo está vindo no DTO atualize o que está no atributo
        pelo dado vindo do json e passado pro DTO
         */
    }

    public void delete() {
        this.ativo = false;
        // esse é o método para inativar um user no banco de dados
    }

    /*
     a anotação @Id indica que o nosso atributo id da classe HooperJPAEntity será usado
     como chave primária da entidade. Usado como identificador único para cada registro
     no banco de dados quando usarmos o Spring Data JPA para persistência.
     */

    /*
    A anotação @GeneratedValue com a estratégia GenerationType.IDENTITY no Spring Data JPA
    é usada em conjunto com a anotação @Id para indicar que o valor da chave primária (id)
    de uma entidade será gerado automaticamente pelo banco de dados.
    Ou seja, nós delegamos essa responsabilidade de gerar um valor para o id
    ao banco de dados.
     */
}
