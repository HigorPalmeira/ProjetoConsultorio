# ProjetoConsultorio

O projeto faz parte dos critérios de avaliação da disciplina de `Programação Orientada a Objetos - POO`. 
<br>
Ele possui requisitos avaliativos:

* Deve possuir pelo menos 2 tabelas/classes no banco de dados.

* Primeiro, ele deve ser entregue o back-end como parte inicial do projeto.

* O front-end deverá ser entregue no final como projeto finalizado.

<br>

O `ProjetoConsultorio` é idealizado como, um programa para gerenciar consultas de um consultório. Foram atribuídas as seguintes classes: `Pacientes`, `Médicos`, `Especialidades` e `Consultas`.

<br>

No programa, poderá ser feito o:

* Cadastro;

* Atualizações;

* Exclusões;

* Listagem, total ou específica por `ID`.

<br>

Foi adotado o padrão de design `Model-View-Controller (MVC)`, dividindo o projeto em três pacotes principais: `model`, `view` e o `controller`. Além, dos pacotes `util` e `service`.

<br>

O projeto possui um arquivo no `main.resources` que armazena o arquivo para criação do banco de dados, o `Script.sql`. 

<br>

É necessário informar suas credenciais (usuário e senha) no arquivo `DAOGenerico.java` ou adicionar em um arquivo `database.properties`.
