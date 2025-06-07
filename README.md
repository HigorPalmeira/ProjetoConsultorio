# ProjetoConsultorio

O projeto faz parte dos critérios de avaliação da disciplina de `Programação Orientada a Objetos - POO`. 
<br>
Ele possui requisitos avaliativos:

* Deve possuir pelo menos 2 tabelas/classes no banco de dados com um relacionamento entre elas.

* Primeiro, ele deve ser entregue o back-end como parte inicial do projeto.

* O front-end deverá ser entregue no final como projeto finalizado.

<br>

O *`ProjetoConsultorio`* é idealizado como, um programa para gerenciar consultas de um consultório, ele é feito em **Java** e utilizando o banco de dados **MySQL**. Foram atribuídas as seguintes classes: `Pacientes`, `Médicos`, `Especialidades` e `Consultas`.

<br>

No programa, poderá ser feito o:

* Cadastro;

* Atualizações;

* Exclusões;

* Listagem, total ou específica por `ID`.

<br>

Foi adotado o padrão de design `Model-View-Controller (MVC)`, dividindo o projeto em três pacotes principais: [`model`](Consultorio/src/main/java/higorpalmeira/com/github/consultorio/model), [`view`](Consultorio/src/main/java/higorpalmeira/com/github/consultorio/view) e o [`controller`](Consultorio/src/main/java/higorpalmeira/com/github/consultorio/controller). Além, dos pacotes [`util`](Consultorio/src/main/java/higorpalmeira/com/github/consultorio/util) e [`service`](Consultorio/src/main/java/higorpalmeira/com/github/consultorio/service).

> Eu ainda estou aprendendo como funciona este padrão e, estudando o paradigma do `POO`. Então, pode ocorrer de haver algo que não está "correto" de acordo com as convenções.

<br>

O projeto possui um arquivo no [`main.resources`](Consultorio/src/main/resources) que armazena o arquivo para criação do banco de dados, o `Script.sql`. 

<br>

É necessário adicionar suas credenciais (usuário e senha) do banco dados, diretamente no arquivo [`DAOGenerico.java`](Consultorio/src/main/java/higorpalmeira/com/github/consultorio/model/dao) ou criar um arquivo de propriedades no `main.resources.config`, com o nome `database.properties` nele adicione:

```database.properties
db.url=URL_DO_BANCO_DE_DADOS
db.username=USUÁRIO
db.password=SENHA_DO_USUÁRIO
db.driver=DRIVER_DO_BANCO_DE_DADOS
```

> O driver utilizado nesse projeto foi o `com.mysql.cj.jdbc.Driver`.
