# segtaf

### Sumário

* [Como rodar o projeto](#como-rodar-o-projeto)
* * [Acessar o projeto via terminal linux](#acessar-o-projeto-via-terminal-linux)
* * [Empacotar o projeto com maven](#empacotar-o-projeto-com-maven)
* * [Executar o projeto](#executar-o-projeto)
* [Banco de Dados](#banco-de-dados)
* * [H2](#h2)
* * [Console H2](#console-h2)
* [API de Produtos](#api-de-produtos)
* * [Introdução da API de Produtos](#introdução-da-api-de-produtos)
* * [Busca de Produto por ID](#busca-de-produtos-por-id)
* * [Alteração de Produto por ID](#alteração-de-produtos-por-id)
* * [Deleção de Produto por ID](#deleção-de-produtos-por-id)
* [Arquitetura do Projeto](#arquitetura-do-projeto)
* * [Clean Architecture](#clean-architecture)
* * [Divisão em Módulos](#divisão-em-módulos)
* * [Relação Entre os Módulos e Clean Architecture](#relação-entre-os-módulos-e-clean-architecture)


# Como Rodar o Projeto

Para rodar o projeto, você vai precisar de:
* Java na versão 17
* Terminal linux (por exemplo o Git Bash)


### Acessar o projeto via terminal linux

Através do terminal linux, você deverá acessar o **diretóio raiz** do projeto.

O **diretóio raiz** é o primeiro diretório do projeto, onde estão todos os arquivos, inclusive o pom.xml.


### Empacotar o projeto com maven

Através do terminal linux, você deverá empacotar o projeto utilizando maven.

Para isso, basta você executar o seguinte comando:

```shell
mvn clean package
```


### Executar o projeto

Com o projeto empacotado, você deverá executar o arquivo **jar** gerado.

Ao empacotar o projeto com maven, é gerado um diretório chamado target.

Dentro desse diretório vao ficar os arquivos compilados, inclusive o pacote jar.

Para executar o pacote jar, basta você executar o seguinte comando:

```shell
java -jar target/segtaf-application.jar
```

Após isso, o projeto ficará em execução em seu terminal (o que irá bloquear o terminal).

Caso você deseje interromper a execução do projeto, basta pressionar **CTRL + C** dentro do terminal.


# Banco de Dados


### H2

O projeto está utilizando o H2 como banco de dados, sendo este um banco de dados relacional que funciona me memória.

O projeto está utilizando uma versão embarcada do H2, sendo assim não há necessidade de instalar nem rodar nada além do próprio projeto.

Por ser um banco de dados em memória em uma versão embarcada, ao parar a execução do projeto, o banco de dados será destruído e todos os dados serão perdidos.

E ao executar o projeto novamente, o banco de dados será recriado. 


### Console H2

É possível acessar o **console** do H2, sendo esta uma interface web para interagir com o banco de dados, executar comandos SQL e etc.

Para conseguir acessar o console, o projeto deve estar execução.

Então basta você acessar o endereço http://localhost:8080/h2-console em seu navegador de internet.

>**Obs:** A porta para acessar o console pode mudar de acordo com a porta em que o projeto estiver rodando.
>
> Pore exemplo, se o projeto estiver rodando na porta 10000, então o console deverá ser acessado através do endereço http://localhost:10000/h2-console. 

Os dados para realizar do login devem ser informados da seguinte maneira:

* **Driver Class:** org.h2.Driver
* **JDBC URL:** jdbc:h2:mem:segtaf
* **User Name:** sa
* **Password:**	

>**Obs:** O campo **Password** deve ser deixado em branco.


# API de Produtos

## Introdução da API de Produtos

## Cadastro de Produtos

### Requisição Cadastro de Produtos

**Endereço**: http://localhost:8080/api/v1/produtos

**Método**: POST

**Exemplo do Corpo da Requisição**:

```json
{
  "nome": "Seguro de Vida Individual",
  "categoria": "VIDA",
  "preco_base": 100.00
}
```

**Exemplo requisição via cURL**

```shell
curl --request POST \
  --url http://localhost:8080/api/v1/produtos \
  --header 'Content-Type: application/json' \
  --data '{
	"nome": "Seguro de Vida Individual",
	"categoria": "VIDA",
	"preco_base": 100.00
}'
```

### Resposta Cadastro de Produtos

**Código da Resposta**: 200 (OK)

**Exemplo do Corpo da Resposta**:

```json
{
  "id": 1,
  "nome": "Seguro de Vida Individual",
  "categoria": "VIDA",
  "preco_base": 100.00,
  "preco_tarifado": 103.20
}
```


## Busca de Produtos por ID

### Requisição Busca de Produtos por ID

**Endereço**: http://localhost:8080/api/v1/produtos/{id}

**Método**: GET

**Exemplo do Corpo da Requisição**:

>**Obs:** para essa requisição não deve ser informado um corpo

**Exemplo requisição via cURL**

```shell
curl --request GET --url http://localhost:8080/api/v1/produtos/1
```

### Resposta Busca de Produtos por ID

**Código da Resposta**: 200 (OK)

**Exemplo do Corpo da Resposta**:

```json
{
  "id": 1,
  "nome": "Seguro de Vida Individual",
  "categoria": "VIDA",
  "preco_base": 100.00,
  "preco_tarifado": 103.20
}
```


## Alteração de Produtos por ID

### Requisição Alteração de Produtos por ID

**Endereço**: http://localhost:8080/api/v1/produtos/{id}

**Método**: PUT

**Exemplo do Corpo da Requisição**:

```json
{
  "nome": "Seguro de automóveis",
  "categoria": "AUTO",
  "preco_base": 50.00
}
```

**Exemplo requisição via cURL**

```shell
curl --request PUT \
  --url http://localhost:8080/api/v1/produtos/1 \
  --header 'Content-Type: application/json' \
  --data '{
	"nome": "Seguro de automóveis",
	"categoria": "AUTO",
	"preco_base": 50.00
}'
```

### Resposta Alteração de Produtos por ID

**Código da Resposta**: 200 (OK)

**Exemplo do Corpo da Resposta**:

```json
{
  "id": 1,
  "nome": "Seguro de automóveis",
  "categoria": "AUTO",
  "preco_base": 50.00,
  "preco_tarifado": 55.25
}
```


## Deleção de Produtos por ID

### Requisição Exclusão de Produtos por ID

**Endereço**: http://localhost:8080/api/v1/produtos/{id}

**Método HTTP**: DELETE

**Exemplo do Corpo da Requisição**:

>**Obs:** para essa requisição não deve ser informado um corpo

**Exemplo requisição via cURL**

```shell
curl --request DELETE --url http://localhost:8080/api/v1/produtos/1
```

### Resposta Alteração de Produtos por ID

**Código da Resposta**: 204 (No Content)

**Exemplo do Corpo da Resposta**:

>**Obs:** para essa requisição não é retornado um corpo na resposta


# Arquitetura do Projeto


## Clean Architecture


## Divisão em Módulos


## Relação Entre os Módulos e Clean Architecture















