# 📝 Java CRUD de Usuários

![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![DBeaver](https://img.shields.io/badge/DBeaver-1F4A88?style=for-the-badge&logo=dbeaver&logoColor=white)

Projeto de CRUD (Create, Read, Update, Delete) em Java para gerenciamento de usuários, com funcionalidades de **cadastro**, **login**, **edição** e **exclusão** de contas. Utiliza MySQL como banco de dados e hash MD5 para criptografar senhas. Além disso, o projeto conta com validações com mensagens de erro amigáveis para o usuário. 😄

## 🚀 Funcionalidades

- 🔐 **Cadastro de Usuário**: Registra novos usuários no sistema.
- 🔑 **Login**: Valida o login com nome de usuário e senha (criptografada).
- ✏️ **Edição de Perfil**: Atualiza informações do usuário, incluindo senha.
- ❌ **Exclusão de Usuário**: Remove a conta do usuário permanentemente.
- ⚠️ **Validações**: Mensagens de erro exibidas para entradas inválidas.

## 🛠 Tecnologias Utilizadas

- **Java**: Linguagem principal de desenvolvimento.
- **MySQL**: Banco de dados para armazenar as informações de usuários.
- **DBeaver**: Ferramenta para criação e gestão do banco de dados.
- **MD5**: Algoritmo de hash para criptografar senhas de usuários.

## ✅ Pré-requisitos

Para executar o projeto, é necessário ter:

- **Java 8** ou superior
- **MySQL** instalado e configurado
- **DBeaver** (opcional) para gerenciamento do banco de dados

## 📋 Configuração do Banco de Dados

1. Abra o DBeaver ou seu gerenciador de banco de dados preferido.
2. Crie um banco de dados no MySQL e nomeie-o como desejar.
3. Execute as seguintes instruções SQL para criar a tabela `usuario`:

    ```sql
    CREATE DATABASE usuario;
    USE bd_login;

    CREATE TABLE usuario (
        id INT PRIMARY KEY AUTO_INCREMENT,
        usuario VARCHAR(50) NOT NULL UNIQUE,
        nome VARCHAR(100) NOT NULL,
        senha VARCHAR(255) NOT NULL
    );
    ```

## ▶️ Como Executar o Projeto

1. Clone o repositório para o seu ambiente local:

    ```bash
    git clone https://github.com/jennieoliveira99/CRUD-JAVA.git
    ```

2. Configure as credenciais de acesso ao banco de dados MySQL no código Java, se necessário.

3. Compile e execute o projeto através de sua IDE preferida (Ex.: Eclipse, IntelliJ).

## 📂 Estrutura do Código

### Classe `Usuario`

A classe `Usuario` é responsável pelas operações principais de CRUD:

- `verificaUsuario`: Realiza a autenticação do usuário com base no nome de usuário e senha.
- `cadastraUsuario`: Insere um novo usuário no banco com senha criptografada.
- `alteraUsuario`: Atualiza informações do usuário (nome e senha) no banco de dados.
- `excluiUsuario`: Exclui um usuário do banco de dados.

### Classe `Conexao`

Gerencia a conexão com o banco de dados, abrindo e fechando as conexões conforme necessário.


## 🤝 Contribuições

Para contribuir com este projeto, faça um fork, crie uma branch com suas alterações e envie um pull request. Toda contribuição é bem-vinda! 😊
