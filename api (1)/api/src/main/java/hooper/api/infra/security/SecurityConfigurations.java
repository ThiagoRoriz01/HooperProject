package hooper.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

// essa classe contem configurações de segurança
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    /*

    Esse trecho de código Java faz parte de uma configuração de segurança usando o Spring Security,
    uma biblioteca popular para implementar a segurança em aplicativos Java baseados na estrutura
    Spring. Vou explicar o que está acontecendo no código:

    public class SecurityConfigurations {: Este código começa definindo uma classe chamada
    SecurityConfigurations. Esta classe provavelmente contém as configurações de segurança para a
    sua aplicação.

    @Bean: Esta anotação é usada para marcar um método como um bean gerenciado pelo Spring.
    Isso significa que o Spring irá gerenciar a criação e configuração desse objeto e o
    disponibilizará para outras partes do aplicativo que precisem dele.

    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {: Este
    método é marcado como um bean e retorna um objeto SecurityFilterChain. O método aceita um
    argumento HttpSecurity chamado http, que é usado para configurar a segurança.

    return http.csrf(csrf -> csrf.disable()): Aqui, estamos configurando a proteção contra
    Cross-Site Request Forgery (CSRF) no contexto do HttpSecurity. O método csrf() é chamado no
    objeto http, e csrf.disable() é chamado para desabilitar a proteção CSRF. Isso significa que
    os tokens CSRF não serão gerados ou verificados neste aplicativo.

    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)): Esta
    linha configura o gerenciamento de sessões. Ela define a política de criação de sessão como
    "STATELESS", o que significa que o aplicativo não manterá o estado da sessão para os usuários.
    Isso é comum em aplicativos RESTful ou API onde a autenticação é baseada em tokens e não é
    necessário manter o estado da sessão do lado do servidor.

    .build();: Finalmente, o método build() é chamado para construir a configuração de segurança e
    retorná-la como um objeto SecurityFilterChain.

    No geral, esse trecho de código está configurando a segurança da sua aplicação Spring para
    desabilitar a proteção CSRF e definir a política de criação de sessão como "STATELESS". Isso
    pode ser apropriado para aplicativos que seguem uma arquitetura RESTful, onde a autenticação é
    baseada em tokens e não há necessidade de manter sessões no servidor.
     */
    }

    @Bean
    public AuthenticationManager AuthenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

        /*
    Claro, vou simplificar os conceitos e explicar o trecho de código de forma direta:

    Bean: Um "bean" no contexto do Spring é como um objeto ou componente que o Spring Framework gerencia para você.
    Imagine que é como um tijolo de construção que você pode usar para construir partes do seu aplicativo. O Spring
    cuida da criação, configuração e gerenciamento desses "tijolos" para você.

    Contêiner Spring: O "contêiner Spring" é como o chefe que supervisiona todos esses tijolos (beans). Ele cria,
    organiza e coordena esses tijolos para que funcionem juntos no seu aplicativo. Isso economiza muito trabalho
    manual ao criar e conectar partes do seu programa.

    Trecho de Código:

    @Bean: Esta anotação indica que estamos configurando um "bean" no contêiner Spring.
    public AuthenticationManager AuthenticationManager(AuthenticationConfiguration configuration) throws Exception:
    Este é um método que cria um bean chamado AuthenticationManager. Ele recebe um objeto AuthenticationConfiguration
    como entrada.
    return configuration.getAuthenticationManager();: Este método retorna o AuthenticationManager configurado com base
    nas configurações contidas no objeto AuthenticationConfiguration. O AuthenticationManager é usado para autenticar
    usuários em um aplicativo Spring.
    Resumindo, o trecho de código está dizendo ao Spring para criar e configurar um "bean" chamado
    AuthenticationManager.
     O AuthenticationManager é importante para a autenticação de usuários em aplicativos Spring. O contêiner Spring
     cuida de toda a mágica nos bastidores para garantir que o AuthenticationManager funcione corretamente no seu
     aplicativo. Seu objetivo é usá-lo para autenticar usuários de maneira eficaz em um aplicativo Spring real.

 */
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
