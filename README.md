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

<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=5,IE=9" ><![endif]-->
<!DOCTYPE html>
<html>
<head>
<title>arquitetura</title>
<meta charset="utf-8"/>
</head>
<body><div class="mxgraph" style="max-width:100%;border:1px solid transparent;" data-mxgraph="{&quot;highlight&quot;:&quot;#0000ff&quot;,&quot;nav&quot;:true,&quot;resize&quot;:true,&quot;toolbar&quot;:&quot;zoom layers lightbox&quot;,&quot;edit&quot;:&quot;_blank&quot;,&quot;xml&quot;:&quot;&lt;mxfile modified=\&quot;2021-02-16T22:16:46.841Z\&quot; host=\&quot;app.diagrams.net\&quot; agent=\&quot;5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36\&quot; etag=\&quot;6nnSjV-eM3ULDLcw0IUj\&quot; version=\&quot;14.3.2\&quot; type=\&quot;device\&quot;&gt;&lt;diagram id=\&quot;Ht1M8jgEwFfnCIfOTk4-\&quot; name=\&quot;Page-1\&quot;&gt;7Vptc9o4EP41zPQ+wPjd8BEMSTNzveMaMr371BFYGF2M5crirb++ki1hWzKBpIRkkvRDaq3llXb3eXbXMi07WG6vCUgXX3AI45ZlhNuWPWxZlml2XfYfl+wKiedZhSAiKBSTSsEt+gmF0BDSFQphVptIMY4pSuvCGU4SOKM1GSAEb+rT5jiur5qCCGqC2xmIdek3FNKFtMvrlTc+QxQtxNJdyy9uLIGcLCzJFiDEm4rIHrXsgGBMi6vlNoAxd570S/Hc1YG7+40RmNBTHrgb/Rzf9ft/35CrNsq6kRv4P9qmIexYg3glTA5ixFTCluXFTPFgSthVxK8+/QXWMGJWkD+ESXQn/YRXNEYJDPZhMNgDEQEh1xXgGBMmS3DCpg/muBS1LNt13IHnMHlGCb6H6mQUx5XJXb9n9jwmD0G2gKFYaA0JRSxof4IpjMc4QxThhN2bYkrxsjKhH6OI36A4ZVIgRjNuL1tgsKDLmI1NsUkBR9OSY2EvXxJkaWHoHG35PgYsvim/udxGnAodsMmcDtv8ipkF+H6+MyDfFwunGPEVR2u2cCYWjPneB2B2HxG8SsKKzfP8H5uih1yggJsHtxWRgMA1xEtIyY5NEXdtgUZBR8cQ400F3FK2qOC6Jzkl+BTtNZeQYxcCdc0I7H2fXH/r+6vBP2sn/v9L+/raH7Y9DX8atmDIKCmGmNAFjnAC4lEprcYNJmGf857PTWEyWaCES+NpLpI4yNEGCG2YmsuvELciDzRTWBnVMVpFbx61PSJZhMju3+rgPz7ouHI43FZvDndiVBjPLT5IcyHK8IrM4CncZgZFkD4QA7sZMk2YIDBmYF7Xd9cECqFuzJFews9y6/hzZXaUKgqrxFPVbKYoso8pKozWFOUY3dv4dNjaGmynq2wG2jOYtldIg7BMDgu4BRFPTYMUEsQ2wdOOlI6liOebzQJReJuCPMQbllGU9MSzjsxPe9xoeaABOQdTg+WdkBvMptxgP1NucDQnrxEIYKqXpv745qDPQ0BBRjGBx716Bi/6Vt2LtuyAKl70Xd2JpuE9kxfdw1B9nCNfM3hd+Yh0uwTlMfDuM9vZ/S5Xqzj+6+h2cprPy2pinuLPWo80n0NvNmsqVqHfmxrGeTzu9Hr1dGHpHncaHO48m7+tN95KmM/VShxtENyLNQi+qRQhRykupzYIXc/quHVVPUNKjjQJLJRgV5mWt+vZ4zddornQeNYOxH+jaN8iugc7u65gnY1KqPPBrqUwpG10DNNTaOJ7R4iSj8aVkvb0RvyhXvEoy0yjdSmaOQpi3d4T+3DH7irQd36TYufu1E29/wlwwspeYLf6NmZ3JsGYab4Za/xhxZHWSXL0iEKItJMF9QBiicIwPtSR1hlxhlptqq296XT0trSpWquvVeer1qe8+JcpSfh1f+pj1uPC5WNAma+TXGIZ9j5a8tzOegSrHySh2+An90zvx8rbg9t9Ii9VRfaF349NvTwFYAlCwGQhZH/6KYEZczrIeejv2fgqKVg9/Dt05PYoPipR9owGOpr+RfnY/eDjCXWyq/WQJ1dK84UZqZ/0f4U/VihDCgPV19PPk8kbLo2OeiJuOi9bGKXiWpyyFLP2+t3HxvVeODamFhu1qsXMXQqhjE93N/oXs9cRpXNXNvUD02sobbKSPTZq/LTuvcTNsf3XFzf9I8tHS2J5Tv1Y6zdaElXVpVsSS//A815Lnecp5ygvXur0Y5OPdrEhTi/eLurnKPXiNgQhzlp8fe33PPmHJ4OFhuVE8F4qneddskNhw/InXkXeLH8oZ49+AQ==&lt;/diagram&gt;&lt;/mxfile&gt;&quot;}"></div>
<script type="text/javascript" src="https://viewer.diagrams.net/js/viewer-static.min.js"></script>
</body>
</html>

<H2>Tecnologias e Padrões</H2>

Dado meu conhecimento e atuação corrente, optei por fazer em **Java**, reduzindo assim a curva de aprendizado de outras tecnologias.
Também foi utilizado o **framework Dropwizard** para implementação da aplicação **RESTfull**. Quanto aos padrões de projetos aplicados, segui o padrão de implementação **REST**, devido ao desafio solicitar trocas de informações por objetos JSON. Dentro da API, também foi implementado o padrão **Factory**, para criação de novos objetos durante as requisições; o mesmo se aplica ao Logger.

Demais documentações quanto ao desenvolvimento então no javadoc da API, no Swagger e, testes. 
