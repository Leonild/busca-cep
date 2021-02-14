# busca-cep
Serviço de buscar CEP e autocompletar os campos do cadastro com as informações do endereço.

<H1>Execução da aplicação</H1>

1. Execute `mvn clean package` para buildar a aplicação
3. Inicie a aplicação com `java -jar target/busca-cep-1.0.jar server etc/development.yml`
4. Para ver aplicação do serviço digite `http://localhost:8080/busca-cep/{CEP}` no navegador.

<H1>Planejamento da soluçao</H1>
Inicialmente pesquisei por formas de integração com a API dos correios. Contudo, encotrei diversos relatos de mal funcionamento
da API dos correios, tais como, problemas quanto ao número de consultas e problemas com integração, necessidade de crawler, etc.

Neste contexto, encontrei um outro provedor de endereços por CEPs, denominado viacep, a qual utilizei para a resolução deste desafio.

**Observação:** para uma implementação em produção, eu estudaria como utilizar/implementar o cep-promise <https://github.com/BrasilAPI/cep-promise>. 
Do que encontrei, aparentemente é a implementação mais limpa e funcional. Contudo, devido ao meu nível de conhecimento em Node.js e, 
objetivo do desafio, decidi implementar desde o início, usando apenas uma base de dados de endereços.

<H2>Tecnologias e Padrões</H2>

Dado meu conhecimento e atuação corrente, optei por fazer em Java, reduzindo assim a curva de aprendizado de outras tecnologias.
Também foi utilizado o framework Dropwizard para implementação da aplicação RESTfull.

TO CONTINUE... 