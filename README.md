# Cartório
Reposítório para armazenar o código referente ao teste da Docket (processo seletivo).

O projeto trabalha com a criação de CRUD para gerenciar cartórios.  

![alt text](https://github.com/RodrigoGrauU/cartorio-os/blob/a2151a999c16e4298de4b2066ad7b2d9f8ef1edb/img/img-CRUD.png)  


Logo na primeira etapa, pensou-se no relacionamento das classes e da entidades do banco de dados, visto que um cartório pode emitir uma ou mais certidões.

![alt text](https://github.com/RodrigoGrauU/cartorio-os/blob/133ab764e642f82c69fe4cf4d9ea7271fa6f1b4a/img/cadstro-cartorio.png)  

Com isso, foi realizado o relacionamento dessas classes utilizando as anotações de persistência do banco de dados.


**Tecnologias utilizadas:**  
Utilizou-se o Spring Initializr (https://start.spring.io/) para iniciar o projeto, adicionando as dependências para trabalhar com projetos web, thymeleaf e conexão com banco de dados Postgres.
Para melhorar um pouco a parte dos formulários, foi utilizado javascript.

Os scripts para criação das tabelas no banco de dados estão localizados na pasta **resource**.

Pendência:  
Falta implementar a API REST da aplicação. Está disponível apenas o endpoint para consumir as informações das certidões disponíveis  