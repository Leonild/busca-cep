# busca-cep
Serviço de buscar CEP e autocompletar os campos do cadastro com as informações do endereço.

<H1>Planejamento da soluçao</H1>
Inicialmente pesquisei por formas de integração com a API dos correios. Contudo, encotrei diversos relatos de mal funcionamento
da API dos correios, tais como, problemas quanto ao número de consultas e falta de fornecimento de uma API Rest.

Neste contexto, encontrei um outro provedor de CEPs, denominado viacep, a qual utilizei para a resolução deste desafio.

**Observação:** para uma implementação em produção, eu estudaria como utilizar/implementar o cep-promise <https://github.com/BrasilAPI/cep-promise>. 
Do que encontrei, aparentemente é a implementação mais limpa e funcional. Contudo, devido ao meu nível de conhecimento em Node.js e, 
objetivo do desafio, decidi implementar desde o inínio, usando apenas um provedor de endereços.

<H2>Tecnologias e Padrões</H2>

Dado meu conhecimento e atuação atual, optei por fazer em Java, reduzindo assim a curva de aprendizado de outras tecnologias.
Também foi utilizado o framework Dropwizard para implementação da aplicação RESTfull.

Quanto aos padrões utilizados, o código foi estruturado no padrão MVC. Além disso, foi utilizado o padrão Factory pra 