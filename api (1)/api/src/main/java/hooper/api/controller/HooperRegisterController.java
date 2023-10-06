package hooper.api.controller;

import hooper.api.domain.hooper.*;
import hooper.api.domain.hooper.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/hoopers")
public class HooperRegisterController {

    /*
    O que nós queremos agora é que o repository persista um hooper no banco de dados.
    pra isso, faremos uso aqui no controller da nossa interface repository.
     */

    /*
    ChatGPT me tirou a seguinte dúvida:
    @Autowired
    private HooperRepository repository; com essa anotação o spring vai criar um
    objeto do tipo HooperRepository?

    Sim, exatamente. A anotação @Autowired no atributo private HooperRepository repository;
    indica ao Spring que ele deve criar automaticamente um objeto (instância) do tipo
    HooperRepository e injetá-lo no atributo repository.
     */
    @Autowired
    private HooperRepository repository;


    @PostMapping
    @Transactional
    /*
    A anotação @Transactional é como um "guardião" que você coloca em torno de partes
    do seu código. Imagine que cada vez que você faça algo importante
    (como atualizar informações no banco de dados), você coloca esse "guardião" para
    garantir que tudo aconteça de forma correta e segura.
     */
    public ResponseEntity register(@RequestBody @Valid HooperRegistrationData data, UriComponentsBuilder uriBuilder) {
        /*
        ATUALIZAÇÃO COMENTÁRIO:

        UriComponentsBuilder é uma classe do spring que cria URI

        a anotação @Valid faz o spring se conectar com o bean validation e
        implementar as anotações do bean no nosso DTO HooperRegistrationData

        nós pegamos nosso DTO e convertemos em um objeto hooper, ou seja,
        uma entidade JPA que é a HooperJPAEntity. Como? usamos o construtor

        criamos um construtor lá na nossa entidade JPA a "HooperJPAEntity"
        que recebe como param o nosso DTO "HooperRegistrationData" e
        guarda os dados que vem da requisição para /hoopers nos atributos da
        nossa entidade JPA a "HooperJPAEntity"

        e aqui abaixo estamos instanciando o construtor dentro do método save
        estamos herdando um método pré-definido da JPARepository que é o (save)
        ele é responsável por fazer o insert no banco de dados.
         */
        var hooper = new HooperJPAEntity(data);
        repository.save(hooper);

        var uri = uriBuilder.path("/hoopers/{id}").buildAndExpand(hooper.getId()).toUri();
        /*
        .path("/hoopers/{id}"): Aqui, você está definindo o caminho da URI.
        Isso está configurando o caminho para "/hoopers/{id}".
        .buildAndExpand(hooper.getId()): Esta parte está substituindo o
        espaço reservado "{id}" no caminho pelo valor real de hooper.getId().
        Basicamente, você está dizendo que o valor de hooper.getId() será
        colocado na posição do "{id}" no caminho da URI.
        .toUri(): Finalmente, você está convertendo o resultado em uma URI
        completa.

         */

        return ResponseEntity.created(uri).body(new HooperDetailingData(hooper));
        /*
        ATUALIZAÇÃO COMENTÁRIO:

         (está retornando uma resposta HTTP 201
        Created com uma URI de localização no cabeçalho)
         e um corpo de resposta contendo os detalhes do objeto hooper encapsulados
         em um DTO HooperDetailingData



    O HooperRegistrationData é um record que criamos para representar cada campo do corpo da req,
    assim podemos acessar um atributo específico do corpo da req ao invés do corpo completo caso
    queiramos.
    isso é chamado de DTO, a forma que usamos para receber e enviar dados da API usando um record
    que é uma classe Java.
     */

        /*
        ao invés de imprimir no console, vamos salvar os dados vindo da API no banco de dados.
        para isso, iremos criar uma entidade JPA, uma classe Java que representa uma tabela
        no banco de dados.
        System.out.println(data);
         */

        // RESUMO DA ALURA DO QUE FOI APRENDIDO ATÉ AQUI:

/*
Mapear requisições POST em uma classe Controller;
Enviar requisições POST para a API utilizando o Insomnia;
Enviar dados para API no formato JSON;
Utilizar a anotação @RequestBody para receber os dados do corpo da requisição em
um parâmetro no Controller;
Utilizar o padrão DTO (Data Transfer Object), via Java Records,
para representar os dados recebidos em uma requisição POST.
 */


    }

    @GetMapping
    public ResponseEntity<Page<HooperDataList>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByAtivoTrue(pagination).map(HooperDataList::new);
        return ResponseEntity.ok(page);
        // retornamos o status 200 ok e passamos como param nossa paginação com o obj page
    }

    /*
     ATUALIZAÇÃO DE COMENTÁRIO:

     o método findAll foi substituido por findAllByAtivoTrue pq o findAll
     retorna todos os registros sendo ativo ou não e esse novo método so-
     mente os registros ativos.
     Existe um padrão de nomemclatura do Spring Data que se usarmos, o Spring
     Data cria o comando SQL que queremos, a query


     ATUALIZAÇÃO DE COMENTÁRIO:

     essa anotação @PageableDefault serve para caso não seja  especificado
     na requisição como o metodo GET deve ser feito, por exemplo, quantos
     registros vão ter em cada pagina, configuramos nessa anotação um padrão.
     Caso sejam passadas especificações na URL, essa anotação é sobrescrita.

     A interface Page<> oferece métodos para acessar informações sobre a
     página atual, como número da página, tamanho da página e total de
     resultados. (os dados retornados podem ser úteis para o frontend,
     no insomnia eles retornam no json com o titulo pageable)

     Pageable não é uma biblioteca separada, mas sim uma interface fornecida
     pelo Spring Framework para facilitar a paginação de resultados de
     consultas em bancos de dados.

     repository.findAll(pagination): Isso pega informações dos jogadores
     de basquete no banco de dados, mas agora estamos usando algo chamado
     "paginação". Imagine que você tem muitos jogadores, então você pega
     um grupo deles de cada vez, como páginas em um livro.

     .map(HooperDataList::new): Agora, para cada página de jogadores que
     pegamos, fazemos algo especial. Imagine que, em cada página, você
     escreve uma pequena nota sobre cada jogador, dizendo algo legal sobre eles.

     return ...: No final, pegamos todas essas "notas" especiais que escrevemos
     para cada jogador e as colocamos em uma nova página, como um novo livro de
     anotações. Esta página tem informações especiais sobre cada jogador.
     */

    // ABAIXO ESTÁ O CÓDIGO COMO ESTAVA ANTES:
    /*
    dentro do nosso List<> estamos passando esse DTO que criamos, esse record/DTO
    contém apenas os dados que queremos devolver da requisição que foi feita.
    Porém o repository.findAll() espera retornar um objeto da nossa entidade JPA a
    HooperJPAEntity, então fazemos essa conversão,para devolver o DTO aplicando o
    método stream() e o map() que recebe um construtor que foi criado no nosso
    DTO HooperDataList. Com o map() estamos convertendo de HooperJPAEntity para
    HooperDataList. Ou seja, ao invés de devolver um objeto da nossa entidade JPA
    vamos devolver nosso DTO
    onde cada atributo do nosso DTO recebe o que vai ser devolvido na requisição/
    frontend por meio do construtor
     */

    /*
    PARA QUE SERVE O MÉTODO stream()

    Em termos mais simples, o método stream() transforma uma coleção (como uma lista)
    ou um array em uma "sequência de elementos" que você pode manipular de várias
    maneiras. Isso é útil para realizar operações como filtrar, mapear, ordenar e
    reduzir dados de maneira mais legível e concisa do que usando loops tradicionais.
     */

    //EXPLICAÇÃO DE CADA MÉTODO:

/*
Nesse código, o método stream() está sendo usado para lidar com uma lista de
informações que vem do banco de dados.

repository.findAll(): Isso pega todas as informações (registros) que estão no
banco de dados. Digamos que sejam as informações dos jogadores de basquete.

.stream(): Isso transforma essas informações em algo que podemos "manipular" um por um,
como se estivéssemos olhando cada jogador individualmente.

.map(HooperDataList::new): Isso faz algo especial para cada jogador. Pode ser como se
estivéssemos criando uma lista com detalhes específicos de cada jogador.

.toList(): No final, pegamos essas coisas especiais que fizemos para cada jogador e as
colocamos em uma lista nova. Agora temos uma lista com os detalhes especiais
de cada jogador.

Basicamente, o stream() ajuda a fazer coisas legais com as informações do banco de dados,
como transformá-las em algo mais útil ou personalizado. É uma maneira de tornar os dados
do banco de dados mais amigáveis para a sua aplicação.
 */

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid HooperDataUpdate data) {

        /*
        criamos esse DTO HooperDataUpdate para atualizarmos somente os dados
        que quisermos.
        Então a requisição será feita, o objeto DTO vai guardar as informações do json e
        temos que salvar essa atualização no banco de dados. Como?
        1. Temos que ir BD e trazer os dados atuais e sobrescrever com as novas informações
        do DTO
         */

        var hooper = repository.getReferenceById(data.id());
         /*
         estamos usando essa var para acessar o BD e carregar os dados atuais.
         buscamos os registros pelo Id.
         pronto, feito isso, nós carregamos o objeto hooper da nossa entidade JPA
         HooperJPAEntity que está com dados desatualizados, queremos atualizar.
         Para fazer essa atualização vamos criar um método na nossa entidade JPA
         HooperJPAEntity
          */
        hooper.updateInformation(data);

        return ResponseEntity.ok(new HooperDetailingData(hooper));
        /*
        nesse método de update é interessante devolver a informação que foi atu
        alizada, fazemos isso usando DTO, n é boa prática devolver a própria
        entidade JPA.
        Quando você passa o objeto hooper como parâmetro para criar o DTO
        HooperDetailingData, você está basicamente dizendo ao programa para
        pegar as informações contidas no objeto hooper e colocá-las dentro
        do DTO HooperDetailingData.
         */
    }

    @DeleteMapping("/{id}")
    @Transactional
    /*
    essa é a anotação para o delete e estamos passando esse param que adiciona
    esse campo a URL, ou seja, temos uma nova url /hoopers/id que é um param
    dinamico, ao invès de ser passado no formato json é passado na url e demos
    o nome de id.
    Mas como fazemos para capturar esse id que vai ser passado na requisição?
    adicionamos esse param que voce ve abaixo no método delete.
     */
    public ResponseEntity delete(@PathVariable Long id) {
/*
essa anotação diz que o id do param é o mesmo da anotação delete lá encima
estamos falando com isso, pegue esse complemento da url e passe como param
no meu metodo
 */
        var hooper = repository.getReferenceById(id);
        hooper.delete();
        /*
         esse método hooper.delete(); foi criado na nossa entidade JPA
         HooperJPAEntity para trocar o atributo ativo de true pra false.
         Quando houver uma exclusão de user na verdade estamos deixando
         ele inativo com esse método
         */

        //repository.deleteById(id);
        // para deletar usamos esse metodo do repository que deleta por id.

        /* não iremos mais usar esse método acima, pois ele exclui do BD,
        no lugar vamos usar o var hooper = repository.getReferenceById(id);
       vamos apenas inativar, pra isso, vamos carregar o dado do banco,
       seter o boolean ativo de true pra false e fazer o update no BD.
         */
        return ResponseEntity.noContent().build();

        /*
        alteramos o retorno do método delete. Ao invés de void agora é
        ResponseEntity e por isso temos que adicionar esse return acima,
        nele passamos o noContent() que retorna um status 204 e o build()
        constroi um objeto do tipo ResponseEntity.

        Em resumo, a classe ResponseEntity em Java (Spring) é usada para
        criar respostas HTTP personalizadas em controladores web Spring,
        permitindo maior controle sobre o conteúdo e os metadados da
        resposta que será enviada de volta ao cliente.

        ResponseEntity SERÁ APLICADO NOS OUTROS MÉTODOS
         */
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {

        var hooper = repository.getReferenceById(id);
        return ResponseEntity.ok(new HooperDetailingData(hooper));
        /*
        criamos esse novo metodo get com o objetivo de detalhar os dados
        de um hooper específico
         */

}
}