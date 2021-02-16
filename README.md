# busca-cep
Serviço de buscar CEP e autocompletar os campos do cadastro com as informações do endereço.

<H1>Execução da aplicação</H1>

1. Execute `mvn clean package` para compilar a aplicação
3. Inicie a aplicação com `java -jar target/busca-cep-1.0.jar server etc/config.yml`
4. Para ver aplicação do serviço digite `http://localhost:8080/busca-cep/{CEP}` no navegador.

A UI com os campos preenchidos de forma automática, está em outro projeto, em <a href="https://github.com/Leonild/busca-cep-ui">UI</a>.

<H1>Planejamento da soluçao</H1>
Inicialmente pesquisei por formas de integração com a API dos correios. Contudo, encotrei diversos relatos de mal funcionamento
da API dos correios, tais como, problemas quanto ao número de consultas e problemas com integração, necessidade de crawler, etc.

Neste contexto, encontrei um outro provedor de endereços por CEPs, denominado viacep, a qual utilizei para a resolução deste desafio.

Para desenvolvimento da UI, embora eu não tenha muita experiência, o ideal seria criar dois projetos independentes. Por 
esse motivo, foi implementada em outro projeto.

**Observação:** para uma implementação em produção, eu estudaria como utilizar/implementar o cep-promise <https://github.com/BrasilAPI/cep-promise>. 
Do que encontrei, aparentemente é a implementação mais limpa e funcional. Contudo, devido ao meu nível de conhecimento em Node.js e, 
objetivo do desafio, decidi implementar desde o início, usando apenas uma base de dados de endereços.

<H2>Arquitetura</H2>

A aplicação segue uma implementação de 4 camadas, onde a primeira é de apresentação, que tem interação com o usuário; a segunda é da aplicação que consome o serviço da API, uma aplicação web simples desenvolvida em React (<a href="https://github.com/Leonild/busca-cep-ui">UI</a>), que possui a lógica de completar os campos com a resposta da API; a terceira é a da API, onde foi implementado o serviço de buscar um endereço a partir de um CEP e, feitos os tratamentos solicitados no desafio; por fim, a quarta camada refere-se a camada de dados, que é composta por uma API externa, que possui toda a base de dados de endereços e CEPs do Brasil e, alimenta a API implementada no desafio. 

De forma abstrata, a figura abaixo exemplifica a conexão e interação entre as camadas:

![alt text](https://i.ibb.co/D8W1Tpd/arquitetura.png)

<H2>Tecnologias e Padrões</H2>

Dado meu conhecimento e atuação corrente, optei por fazer em **Java**, reduzindo assim a curva de aprendizado de outras tecnologias.
Também foi utilizado o **framework Dropwizard** para implementação da aplicação **RESTfull**. Quanto aos padrões de projetos aplicados, segui o padrão de implementação **REST**, devido ao desafio solicitar trocas de informações por objetos JSON. Dentro da API, também foi implementado o padrão **Factory**, para criação de novos objetos durante as requisições; o mesmo se aplica ao Logger.

Demais documentações quanto ao desenvolvimento então no javadoc da API, no Swagger e, testes. 
