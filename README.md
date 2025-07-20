# API de Cadastro de Infrações

Serviços desenvolvidos utilizando:

* Java 17
* Spring Boot 3
* Maven
* PostgreSQL
* JWT Bearer
* Docker
* Swagger UI / OpenAPI 3
* Domain-Driven Design


## Instruções de uso da aplicação:

1. Importe as collection que se encontra no path /collection no Postman.
2. Pode ser utilizado a aplicação também via swagger http://localhost:8080/swagger-ui/index.html#/
3. Para rodar a aplicação, execute o comando `mvn clean install -e` na raiz do projeto para gerar o .jar.
4. E com isso, execute o comando `docker compose up` na raiz do projeto para iniciar a aplicação.
5. Primeiro, para iniciar faça a criação do usuário em /register.
5. Após a criação do usuário, faça o login em /login para obter o token JWT.
6. Nisso, já é possível acessar os endpoints protegidos com o token JWT obtido no login.


Qual foi o aspecto mais difícil deste desafio e por quê?
* Foi realizar a implementação do JWT Bearer, pois não tinha feito projetos utilizando JWT antes, pois utilizamos o Keycloak para autenticação e autorização em outros projetos. Mas foi um desafio interessante e que agregou muito conhecimento.