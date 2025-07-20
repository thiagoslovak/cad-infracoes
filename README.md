# API de Cadastro de Infra��es

Servi�os desenvolvidos utilizando:

* Java 17
* Spring Boot 3
* Maven
* PostgreSQL
* JWT Bearer
* Docker
* Swagger UI / OpenAPI 3
* Domain-Driven Design


## Instru��es de uso da aplica��o:

1. Importe as collection que se encontra no path /collection no Postman.
2. Pode ser utilizado a aplica��o tamb�m via swagger http://localhost:8080/swagger-ui/index.html#/
3. Para rodar a aplica��o, execute o comando `mvn clean install -e` na raiz do projeto para gerar o .jar.
4. E com isso, execute o comando `docker compose up` na raiz do projeto para iniciar a aplica��o.
5. Primeiro, para iniciar fa�a a cria��o do usu�rio em /register.
5. Ap�s a cria��o do usu�rio, fa�a o login em /login para obter o token JWT.
6. Nisso, j� � poss�vel acessar os endpoints protegidos com o token JWT obtido no login.


Qual foi o aspecto mais dif�cil deste desafio e por qu�?
* Foi realizar a implementa��o do JWT Bearer, pois n�o tinha feito projetos utilizando JWT antes, pois utilizamos o Keycloak para autentica��o e autoriza��o em outros projetos. Mas foi um desafio interessante e que agregou muito conhecimento.