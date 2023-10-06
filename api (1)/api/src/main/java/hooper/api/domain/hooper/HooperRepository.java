package hooper.api.domain.hooper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Nós mapeamos nossa entidade JPA (HooperJPAEntity) agora precisamos fazer a persistência
dos dados no banco de dados, que é pegar um objeto (hooper) um usuário, e salvar no
banco de dados. Para fazer isso vamos usar esse arquivo com o conceito de (Repository)
que é uma interface
 */
public interface HooperRepository extends JpaRepository<HooperJPAEntity, Long> {
    Page<HooperJPAEntity>findAllByAtivoTrue(Pageable pagination);
    /*
    ATUALIZAÇÃO DE COMENTÁRIO:
acima criamos o método que retorna um page de registros ativos


    Em resumo, o JpaRepository do Spring Data JPA simplifica drasticamente a
    interação com o banco de dados, fornecendo métodos pré-definidos e
    permitindo que você crie consultas personalizadas de forma fácil e
    eficiente. Isso torna o trabalho com banco de dados mais rápido e
    menos propenso a erros.

    Para ela funcionar é necessário passar 2 params no generics:
    1. A entidade JPA que representa a tabela no banco de dados que você
    quer se comunicar
    2. O tipo de dado que você definiu para o seu Id, sua chave primária
     */
}
