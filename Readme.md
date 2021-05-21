# gerenciamentoPauta

### Desafio:

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias,
por votação. Imagine que você deve criar uma solução backend para gerenciar essas sessões de
votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de
uma API REST:

● Cadastrar uma nova pauta;

● Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um
tempo determinado na chamada de abertura ou 1 minuto por default);

● Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado
é identificado por um id único e pode votar apenas uma vez por pauta);

● Contabilizar os votos e dar o resultado da votação na pauta
Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as
interfaces pode ser considerada como autorizada. A escolha da linguagem, frameworks e
bibliotecas é livre (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart
da aplicação.

### Sobre

Gerenciamento de pauta é uma aplicação que atravéz de APIs gerencia as pautas e contabiliza os votos realizados nas sessões.

####Endpoints Externalizados:

#####Pauta
`Get - http://localhost:9080/v1/pauta`  - Vizualização de todas as pautas cadastradas.

`Get - http://localhost:9080/v1/pauta/{PautaId}` - Vizualização de uma pauta cadastrada pelo seu Id.

`Post - http://localhost:9080/v1/pauta` - inserção da pauta desejada.

#####Sessão

`Get - http://localhost:9080/v1/sessao`  - Vizualização de todas as sessões cadastradas.

`Get - http://localhost:9080/v1/sessao/{PautaId}` - Vizualização de uma sessão cadastrada pela seu id de pauta.

`Post - http://localhost:9080/v1/sessao` - inserção da sessão desejada.

`GET - http://localhost:9080/v1/sessao/{PautaId}/resultado` - Vizualização do resultado de uma sessão.


#####Voto

`http://localhost:9080/v1/voto` - Realização do voto desejado.


Mais informações sobre os endpoints apresentados pode ser visto acessando o swagger da aplicação pelo endereço:

`http://localhost:9080/swagger-ui.html` 


##Arquitetura

Esta aplicação foi desenvolvida em JAVA8, utilizando os frameworks:
* SpringBoot;
* Lombok;
* Swagger;
* Logstash;

Já para realização de build da aplicação utilizou-se Gradle e alguns frameworks para melhorar a qualidade do código como:
* Checkstyle;
* CodeNarc;

E para realização dos testes foi utilizado Groovy.

##Implementações Futuras

* Converter para uma abordagem reativa;
* Melhorar cobertura de código
* Implementar mensageria utilizando kafka para  informar outras aplicações
* teste de performance

 

