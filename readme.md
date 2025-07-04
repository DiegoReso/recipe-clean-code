[![Licença MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.0-blue.svg?logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?logo=postgresql&logoColor=white)

# Recipe API

Uma API desenvolvida com **Spring Boot** para gerenciamento de receitas culinárias, seguindo os princípios de Clean Architecture e com suporte a operações CRUD.

---

## Visão Geral

A Recipe API oferece endpoints RESTful para gerenciamento completo de receitas, incluindo criação, busca, atualização e remoção, com uma estrutura limpa e desacoplada.

---

## Tecnologias Utilizadas

* **Spring Boot 3**: Framework para construção de aplicações Java
* **Spring Data JPA**: Integração com banco de dados
* **PostgreSQL**: Banco de dados relacional
* **Lombok**: Redução de código boilerplate
* **MapStruct**: Mapeamento entre DTOs e entidades
* **Docker**: Containerização da aplicação
* **Flyway**: Controle de migrations do banco de dados
* **Maven**: Gerenciamento de dependências

---

## Como Executar o Projeto

### Pré-requisitos:

* Docker e Docker Compose instalados
* `diegoreso/image-recipe` imagem da aplicação spring boot
* Imagem do `postgres:latest` para realizar a persistência dos dados


1.  **Configure o Docker Compose:**
    Certifique-se de que seu arquivo `docker-compose.yaml` na raiz do projeto está configurado para iniciar o PostgreSQL e sua aplicação. Um exemplo comum seria:

    ```yaml
    services:
      postgres:
        image: postgres:latest
        container_name: RecipeIt
        environment:
          POSTGRES_DB: RecipeIt
          POSTGRES_USER: postgres
          POSTGRES_PASSWORD: postgres
        ports:
          - "5431:5432"
        restart: always

      recipe-app:
        build: .
        container_name: image-recipe:0.0.1-SNAPSHOT
        ports:
          - "8080:8080"
        environment:
          SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/RecipeIt
          SPRING_DATASOURCE_USERNAME: postgres
          SPRING_DATASOURCE_PASSWORD: postgres
        depends_on:
          - postgres
        restart: on-failure
    ```

2.  **Inicie os serviços com Docker Compose:**
    No diretório raiz do projeto, onde o `docker-compose.yaml` está localizado, execute:
    ```bash
    docker-compose up --build -d
    ```
    * `--build`: Garante que a imagem da sua aplicação seja construída.
    * `-d`: Inicia os containers em modo "detached" (em segundo plano).

3.  **Verifique o status:**
    ```bash
    docker-compose ps
    ```
    Você deve ver os containers `postgres` e `recipe-app` com status `Up`.

4.  **Acesse a API:**
    A API estará disponível em `http://localhost:8080`.

---

### 1. Criar Nova Receita

* **Endpoint**: `POST /api/recipe`
* **Descrição**: Adiciona uma nova receita ao sistema.
* **Corpo da Requisição (JSON)**:
    ```json
    {
      "name": "Moqueca Vegana de Palmito",
      "description": "Uma deliciosa moqueca vegana com palmito, pimentões coloridos e leite de coco, cheia de sabor e aroma.",
      "identification": "MD1234",
      "instruction": "1. Em uma panela de barro, refogue cebola e alho no azeite de dendê. 2. Adicione os pimentões, tomates e palmito. 3. Despeje o leite de coco e tempere com coentro e sal. 4. Cozinhe em fogo baixo até engrossar. 5. Sirva com arroz branco e farofa.",
      "preparationTimeMinutes": 45.0,
      "servings": 6.0,
      "type": "VEGAN"
    }
    ```
* **Resposta de Sucesso (JSON - Status 201 Created)**:
    ```json
    {
      "id": "1",
      "name": "Moqueca Vegana de Palmito",
      "description": "Uma deliciosa moqueca vegana com palmito, pimentões coloridos e leite de coco, cheia de sabor e aroma.",
      "identification": "MD1234",
      "instruction": "1. Em uma panela de barro, refogue cebola e alho no azeite de dendê. 2. Adicione os pimentões, tomates e palmito. 3. Despeje o leite de coco e tempere com coentro e sal. 4. Cozinhe em fogo baixo até engrossar. 5. Sirva com arroz branco e farofa.",
      "preparationTimeMinutes": 45.0,
      "servings": 6.0,
      "createdAt": "2025-06-10T10:15:00",
      "updatedAt": "2025-06-13T19:26:35.535948",
      "type": "VEGAN"
    }
    ```
* **Possíveis Erros**: `400 Bad Request` (dados inválidos)

### 2. Listar Todas as Receitas

* **Endpoint**: `GET /api/recipe`
* **Descrição**: Retorna uma lista de todas as receitas cadastradas no sistema.
* **Resposta de Sucesso (JSON - Status 200 OK)**:
    ```json
    [
      {
        "id": "1",
        "name": "Moqueca Vegana de Palmito",
        "description": "...",
        "identification": "MD1234",
        "instruction": "...",
        "preparationTimeMinutes": 45.0,
        "servings": 6.0,
        "createdAt": "2025-06-10T10:15:00",
        "updatedAt": "2025-06-13T19:26:35.535948",
        "type": "VEGAN"
      },
      {
        "id": "2",
        "name": "Salada Caesar",
        "description": "...",
        "identification": "MD4321",
        "instruction": "...",
        "preparationTimeMinutes": 20.0,
        "servings": 2.0,
        "createdAt": "2025-06-09T09:00:00",
        "updatedAt": "2025-06-09T09:00:00",
        "type": "TRADITIONAL"
      }
    ]
    ```
* **Possíveis Erros**: N/A (retorna uma lista vazia se não houver resultados)

### 3. Buscar Receita por ID

* **Endpoint**: `GET /api/recipe/{id}`
* **Descrição**: Retorna os detalhes de uma receita específica utilizando seu identificador único.
* **Parâmetros de Path**:
    * `id` (string/UUID): O identificador único da receita.
* **Exemplo de Requisição**: `GET /api/recipe/1`
* **Resposta de Sucesso (JSON - Status 200 OK)**:
    ```json
    {
      "id": "1",
      "name": "Moqueca Vegana de Palmito",
      "description": "Uma deliciosa moqueca vegana com palmito, pimentões coloridos e leite de coco, cheia de sabor e aroma.",
      "identification": "MD1234",
      "instruction": "1. Em uma panela de barro, refogue cebola e alho no azeite de dendê. 2. Adicione os pimentões, tomates e palmito. 3. Despeje o leite de coco e tempere com coentro e sal. 4. Cozinhe em fogo baixo até engrossar. 5. Sirva com arroz branco e farofa.",
      "preparationTimeMinutes": 45.0,
      "servings": 6.0,
      "createdAt": "2025-06-10T10:15:00",
      "updatedAt": "2025-06-13T19:26:35.535948",
      "type": "VEGAN"
    }
    ```
* **Possíveis Erros**: `404 Not Found` (receita não encontrada)

### 4. Atualizar Receita

* **Endpoint**: `PUT /api/recipe/{id}`
* **Descrição**: Atualiza os dados de uma receita existente.
* **Parâmetros de Path**:
    * `id` (string/UUID): O identificador único da receita a ser atualizada.
* **Corpo da Requisição (JSON)**:
    * Envie um JSON com os campos da receita que deseja atualizar. Apenas os campos presentes serão modificados.
    ```json
    {
      "name": "Moqueca Vegana de Palmito Refinada",
      "preparationTimeMinutes": 55.0
    }
    ```
* **Resposta de Sucesso (JSON - Status 200 OK)**:
    ```json
    {
      "id": "1",
      "name": "Moqueca Vegana de Palmito Refinada",
      "description": "Uma deliciosa moqueca vegana com palmito, pimentões coloridos e leite de coco, cheia de sabor e aroma.",
      "identification": "MD1234",
      "instruction": "1. Em uma panela de barro, refogue cebola e alho no azeite de dendê. 2. Adicione os pimentões, tomates e palmito. 3. Despeje o leite de coco e tempere com coentro e sal. 4. Cozinhe em fogo baixo até engrossar. 5. Sirva com arroz branco e farofa.",
      "preparationTimeMinutes": 55.0,
      "servings": 6.0,
      "createdAt": "2025-06-10T10:15:00",
      "updatedAt": "2025-06-13T19:26:35.535948",
      "type": "VEGAN"
    }
    ```
* **Possíveis Erros**: `400 Bad Request` (dados inválidos), `404 Not Found` (receita não encontrada)

### 5. Deletar Receita

* **Endpoint**: `DELETE /api/recipe/{id}`
* **Descrição**: Remove uma receita do sistema utilizando seu identificador único.
* **Parâmetros de Path**:
    * `id` (string/UUID): O identificador único da receita a ser deletada.
* **Exemplo de Requisição**: `DELETE /api/recipe/1`
* **Resposta de Sucesso (Status 204 No Content)**:
    * Nenhuma resposta no corpo.
* **Possíveis Erros**: `404 Not Found` (receita não encontrada)

  
