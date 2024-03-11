# segtaf

### Sumário

* [Como rodar o projeto](#como-rodar-o-projeto)
* * [Passo a passo para rodar o projeto](#passo-a-passo-para-rodar-o-projeto)
* * * [Acessar o projeto via terminal linux](#acessar-o-projeto-via-terminal-linux)
* * * [Empacotar o projeto com maven](#empacotar-o-projeto-com-maven)
* * * [Executar o projeto](#executar-o-projeto)
* * [Banco de Dados](#banco-de-dados)
* * * [H2](#h2)
* * * [Console H2](#console-h2)
* [Soluções do Projeto](#soluções-do-projeto)
* * [Arquitetura do Projeto](#arquitetura-do-projeto)
* * * [Clean Architecture](#clean-architecture)
* * * [Módulos do Projeto](#módulos-do-projeto)
* * * [Relação Entre os Módulos e Clean Architecture](#relação-entre-os-módulos-e-clean-architecture)
* * * [Cálculo do Preço Tarifado do Produto](#cálculo-do-preço-tarifado-do-produto)
* [API de Produtos](#api-de-produtos)
* * [Introdução da API de Produtos](#introdução-da-api-de-produtos)
* * [Busca de Produto por ID](#busca-de-produtos-por-id)
* * [Alteração de Produto por ID](#alteração-de-produtos-por-id)
* * [Deleção de Produto por ID](#deleção-de-produtos-por-id)


# Como Rodar o Projeto

## Passo a passo para rodar o projeto

Para rodar o projeto, você vai precisar de:
* Java na versão 17
* Maven na versão 3.6.3
* Terminal linux (por exemplo o Git Bash)


### Acessar o projeto via terminal linux

Através do terminal linux, você deverá acessar o **diretóio raiz** do projeto.

O **diretóio raiz** é o primeiro diretório do projeto, onde estão todos os arquivos, inclusive o pom.xml.


### Empacotar o projeto com maven

Através do terminal linux, você deverá compilar e empacotar o projeto utilizando maven.

Para isso, basta você executar o seguinte comando:

```shell
mvn clean package
```


### Executar o projeto

Com o projeto empacotado, você deverá executar o arquivo **jar** gerado.

Ao empacotar o projeto com maven, é gerado um diretório chamado **target**.

Dentro desse diretório vão ficar os arquivos compilados, inclusive o pacote **segtaf-application.jar**.

Para executar o pacote jar, basta você executar o seguinte comando:

```shell
java -jar target/segtaf-application.jar
```

Após isso, o projeto ficará em execução em seu terminal (o que irá bloquear o terminal).

Caso você deseje interromper a execução do projeto, basta pressionar **CTRL + C** dentro do terminal.


## Banco de Dados

### H2

O projeto está utilizando o H2 como banco de dados, sendo este um banco de dados relacional que funciona em memória.

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


# Soluções do Projeto

## Arquitetura do Projeto

### Clean Architecture

O projeto foi desenvolvido seguindo os padrões de Clean Architecture.

Para representar da melhor forma a divisão em camadas, o projeto foi divido em módulos utilizando o maven.

Garantindo assim que os módulos das camadas mais internas nunca tenham acesso às camadas mais externas.

Porque foi seguida essa abordagem?

Se o projeto fosse desenvolvido em single module, isso permitiria que classes das camadas mais internas enxergassem as classes das camadas mais externas. 

Então seguindo uma abordagem multi module, foi possível configurar corretamente a dependências entre as camadas, seguindo os padrões de Clean Architecture. 


### Módulos do Projeto

* **segtaf-domain-business:** contém as regras de negócio e casos de uso do domínio de Produto de Seguro. Não utiliza frameworks nem bibliotecas.  
* **segtaf-application-business:** contém as regras de negócio e casos de uso da aplicação e orquestra os casos de uso do domínio. Não utiliza frameworks nem bibliotecas.
* **segtaf-rest-api:** expõe os endpoints da API Rest.  
* **segtaf-application:** este é o módulo "mãe" do projeto, onde orquestramos os outros módulos.
* **segtaf-infrastructure:** módulo responsável por realizar as configurações do framework.
* **segtaf-database:** módulo responsável por realizar a comunicação com o banco de dados.


### Relação Entre os Módulos e Clean Architecture

* **Camada:** Enterprise Business Rules (Entities)
* * **Módulo:** segtaf-domain-business
* **Camada:** Application Business Rules (Use Cases)
* * **Módulo:** segtaf-application-business
* **Camada:** Interface Adapters (Controllers)
* * **Módulo:** segtaf-rest-api
* **Camada:** Frameworks & Drivers (Database)
* * **Módulo:** segtaf-application
* * **Módulo:** segtaf-infrastructure
* * **Módulo:** segtaf-database


## Cálculo do Preço Tarifado do Produto

Por se tratar de uma regra de negócio do domínio, o cálculo do preço tarifado foi implementado no módulo segtaf-domain-business.

Para a solução do cáculo foi utilizado 03 design patterns.

### Strategy

A aplicação possui valores diferentes para realizar o cálculo de cada categoria de seguro.

Sendo assim se utilizássemos diversos if, a aplicação ficaria com difícil manutenção e extensão, caso seja necessário adiconar novas categorias no futuro ou mudar os valores atuais das categorias. 

A intenção foi evitar essa situação de ter uma única classe com todas as regras contendo divesos "ifs":

```java
if (categoria == "VIDA") {
    return precoBase + (precoBase * 0.01) + (precoBase * 0.022) + (precoBase * 0.00);
}

if (categoria == "AUTO") {
    return precoBase + (precoBase * 0.055) + (precoBase * 0.04) + (precoBase * 0.01);
}
```

Seguindo o Design Pattern Strategy, a solução foi criar uma classe diferente para realizar o cáculo de cada categoria.


### Template Method

Identifiquei que todas as classes que realizam o cálculo do valor tarifado da categoria possuem um mesmo padrão de funcionamento.

Sendo assim, seguindo o Design Pattern Template Method, eu criei uma classe abstrata que implementa toda a regra para o cáculo.

Porém essa classe abstrata não possui os valores de IOF, PIS e COFINS.

Sendo assim, esses valores devem ser informados via construtor pelas classes que estendem essa classe abstrata.

A vantagem de utilizar esse design pattern é evitar a repetição de código para um compotamento que é em comum entre diversas classes. 

**Exemplo da classe abstrata:**

```java
public abstract class CalculadoraTarifaCategoria {

    public static final int CEM_POR_CENTO = 100;
    private final Categoria categoria;
    private final Double iof;
    private final Double pis;
    private final Double cofins;
    
    public CalculadoraTarifaCategoria(final Categoria categoria, final Double iof, final Double pis, final Double cofins) {
        this.categoria = categoria;
        this.iof = iof;
        this.pis = pis;
        this.cofins = cofins;
    }

    public BigDecimal calcularPrecoTarifado(final Categoria categoria, final BigDecimal precoBase) {
        return precoBase
            .add(calcularValorTarifa(precoBase, iof))
            .add(calcularValorTarifa(precoBase, pis))
            .add(calcularValorTarifa(precoBase, cofins))
            .setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calcularValorTarifa(final BigDecimal valorBase, final Double porcentagem) {
        return valorBase
            .divide(BigDecimal.valueOf(CEM_POR_CENTO))
            .multiply(BigDecimal.valueOf(porcentagem));
    }
}
```

>**Obs.:** este é apenas um exemplo, a classe real implementa no projeto está mais completa.

**Exemplo da classe que implementa a classe abstrata:**

```java
public class CalculadoraTarifaCategoriaVida extends CalculadoraTarifaCategoria {
    public CalculadoraTarifaCategoriaVida() {
        super(Categoria.VIDA, 1.0d, 2.2d, 0.0d);
    }
}
```


### Chain Of Responsibility

Todo cálculo de preço tarifado é realizado para alguma categoria.

Conforme explicado anteriormente, temos diversas classes que realizam o cálculo, uma para cada categoria.

Então como saber em tempo de execução qual classe devo utilizar para realizar o cálculo? Usando o design pattern Chain Of Responsibility!

Usando esse design pattern eu não preciso informar qual classe irá realizar o cálculo.

Eu só preciso informar a categoria, então entre as classes que realizam de cálculo, será verificado qual deles pode calcular o preco tarifado da categoria que foi informada. 

**Exemplo:**

```java
public static CalculadoraTarifaCategoria construirCorrenteCalculadoras(final CalculadoraTarifaCategoria... calculadoras) {
    for (int i = 1; i < calculadoras.length; i++) {
        calculadoras[i-1].proximaCalculadora = calculadoras[i];
    }
    return calculadoras[0];
}

public BigDecimal calcularPrecoTarifado(final Categoria categoria, final BigDecimal precoBase) {

    if (!consigoCalcularCategoria(categoria)) {
        return proximaCalculadora.calcularPrecoTarifado(categoria, precoBase);
    }

    return precoBase
        .add(calcularValorTarifa(precoBase, iof))
        .add(calcularValorTarifa(precoBase, pis))
        .add(calcularValorTarifa(precoBase, cofins))
        .setScale(2, RoundingMode.HALF_EVEN);
}
```

# API de Produtos

## Introdução da API de Produtos

A API de Produtos é uma API Rest que fornece endpoints para realizar o CRUD dos Produtos de Seguro.

Na raiz do projeto está disponível a collection do insomnia, **api-produtos-insomnia-collection-2024-03-08.json**, para facilitar o envio de requisições para a API. 

Logo abaixo também há explicações de como funciona cada endpoint da API, contando inclusive com exemplos em **cURL**.


## Distributed Tracing

O projeto está preparado para o Distributed Tracing.

Isso significa que é possível identificar o ciclo de vida de uma requisição dentro do projeto.

Assim como também o ciclo de vida de uma requisição entre diferentes sistemas.

Para isso basta informar o header **traceparent** seguindo os padrões na documentação https://www.w3.org/TR/trace-context.

E caso o header traceparent não seja informado, a aplicação irá gerar um automaticamente.


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
