# Cadastro de Pessoas

## Sobre o projeto
- O projeto simula um cadastro simples de Pessoa, contendo nome, idade, sexo e o Endereço. Onde o endereço é composto por uf, cidade, logradouro, cep. As duas tabelas possuem um relacionamento ManyToOne, onde uma Pessoa pode possuir vários endereços.
As tecnologias utilizadas foram Java na versão 8 juntamente com os frameworks JSF e PrimeFaces para a criação da página WEB e Hibernate para facilitar o backend e auxiliar na conexão com o banco dados, que neste projeto foi utilizado o Postgres.

## Executando o Código
- Para executar o código é necessário utilizar alguma IDE de sua prefêrencia, porém eu recomendo a utilização do Eclipse, podendo ser Intellij, VSCode, lembrando que é necessário verificar o suporte da mesma com o JavaEE.
- Após a instalação da IDE e clonar o projeto em sua maáquina é necessário baixar o tomcat 9 para subir a aplicação em um server e baixar o PostgreSQL e configura-lo através do pgAdmin.
- Na IDE é necessário configurar um server local utilizando o Tomcat 9 baixado anteriormente e adicionar o projeto no mesmo. Após iniciar o server basta acessar localhost:8080/cadastro-pessoas/FirstPage.xhtml(ou trocar pela porta que você configurou o server).

OBS: Antes de iniciar o projeto é preciso acessar o arquivo persistence.xml e colocar na url o Banco de dados que será utilizado, o usuário e a senha para o hibernate conectar no banco e criar as tabelas.

## Testando conexão com o banco
- Para testar se a conexão esta funcionando, basta rodar a classe TestConnection como Java application.
