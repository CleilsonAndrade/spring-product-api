<div align="center">
  <h1>Spring Product API</h1>
  <p>Spring Product API é uma API simples de um CRUD de produtos, desenvolvida com o framework Spring e JPA persistindo os dados em um banco de dados MySQL.</p>
  <img src="./references/flow.png" alt="Flow" width="800">
</div>

# 📒 Índice
* [Descrição](#descrição)
* [Requisitos Funcionais](#requisitos)
* [Tecnologias](#tecnologias)
* [Endpoints](#endpoints)
* [Instalação](#instalação)
* [Licença](#licença)

# 📃 <span id="descrição">Descrição</span>
Spring Product API é uma API simples de um CRUD de produtos, desenvolvida com o framework [**Spring**](https://spring.io/) e [**JPA**](https://spring.io/projects/spring-data-jpa) persistindo os dados em um banco de dados [**MySQL**](https://www.mysql.com/) e algumas das novas features apresentadas na versão 17 do Java como records para o auxilio no desenvolvimento de DTOs.

# 📌 <span id="requisitos">Requisitos Funcionais</span>
- [x] Cadastro de produtos<br>
- [x] Exibir informações de um produto especifico<br>
- [x] Listagem de todos os produtos cadastrados<br>
- [x] Atualização de informações de um produto<br>
- [x] Deletar um produto<br>

# 💻 <span id="tecnologias">Tecnologias</span>
- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Boot DevTools**
- **Spring Data JPA**
- **MySQL Driver**

# 📍 <span id="endpoints">Endpoints</span>
| Método | Endpoint               | Resumo                                          
|--------|----------------------|-----------------------------------------------------
<kbd>POST</kbd> | <kbd>/products</kbd> | Responsável por cadastrar um produto
<kbd>GET</kbd> | <kbd>/products/:id</kbd> | Responsável por exibir as informações de um produto, informando o ID
<kbd>GET</kbd> | <kbd>/products</kbd> | Responsável por listar todos os produtos cadastrados
<kbd>PUT</kbd> | <kbd>/products/:id</kbd> | Responsável por atualizar as informações de um produto, informando o ID
<kbd>DELETE</kbd> | <kbd>/products/:id</kbd> | Responsável por deletar produto, informando o ID

# 🚀 <span id="instalação">Instalação</span>
```bash
  # Clone este repositório:
  $ git clone https://github.com/CleilsonAndrade/spring-product-api.git
  $ cd ./spring-product-api

  # Instalar as dependências:
  $ mvn clean install

  # Executar:
  $ mvn spring-boot:run
```

# 📝 <span id="licença">Licença</span>
Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<p align="center">
  Feito com 💜 by CleilsonAndrade
</p>
