# Cartório
Reposítório para armazenar o código referente ao teste da Docket (processo seletivo).

O projeto trabalha com a criação de CRUD para gerenciar cartórios.
Logo na primeira etapa, pensou-se no relacionamento das classes e da entidades do banco de dados, visto que um cartório pode emitir uma ou mais certidões.

Com isso, foi realizado o relacionamento dessas classes utilizando as anotações de persistência do banco de dados.


**Tecnologias utilizadas:**  
Utilizou-se o Spring Initializr (https://start.spring.io/) para iniciar o projeto, adicionando as dependências para trabalhar com projetos web, thymeleaf e conexão com banco de dados Postgres.
Para melhorar um pouco a parte dos formulários, foi utilizado javascript.

Pendência:  
Falta implementar a API REST da aplicação. Está disponível apenas o endpoint para consumir as informações das certidões disponíveis  