package hooper.api.domain.hooper.user;


//Essa classe tem a lógica de autenticação do projeto

//Toda classe que queremos que o Spring carregue precisa ter anotações

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Essa anotação diz pro Spring que essa classe executa algum serviço no caso o de autenticação.
@Service
public class ServiceAuthentication implements UserDetailsService {
    /*
    implements UserDetailsService: A classe ServiceAuthentication está implementando a interface
    UserDetailsService. Essa interface é parte do Spring Security e é usada para carregar
    detalhes do usuário durante o processo de autenticação.
     */

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);

        /*
        loadUserByUsername(String username): Este método é definido pela interface
        UserDetailsService e deve ser implementado pela classe que a implementa.
        Esse método é usado para carregar os detalhes do usuário com base no
        nome de usuário (username) fornecido como parâmetro. É aqui que você deve
        implementar a lógica para carregar os detalhes do usuário, como seu nome
        de usuário, senha e as autorizações (papéis) associadas a ele.

        throws UsernameNotFoundException: Este método pode lançar uma exceção do
        tipo UsernameNotFoundException se o usuário com o nome de usuário fornecido
        não for encontrado. Isso é útil para indicar que o usuário não existe e a
        autenticação falhou.

        No return implementamos anlógica necessária para consultar um banco de dados
        para obter os detalhes do usuário com base no login.
         */
    }
}
