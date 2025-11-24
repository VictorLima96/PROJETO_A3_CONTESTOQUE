# JavaParcel - Sistema de Gerenciamento de Encomendas

## 📦 Sobre o Projeto

O **JavaParcel** é um sistema completo de gerenciamento e rastreamento de encomendas desenvolvido em Java, criado como parte da **Atividade A3 do 2º Semestre** da disciplina de **Programação de Soluções Computacionais** da **Universidade São Judas**.

O projeto consiste em uma aplicação desktop que oferece duas funcionalidades principais:

- **Painel Administrativo**: Sistema com autenticação para gerenciar encomendas (cadastro, edição e exclusão)
- **Sistema de Rastreio**: Interface pública para consultar o status e localização de encomendas

## 🎯 Contexto

Este sistema foi desenvolvido para simular um ambiente real de gestão logística, onde administradores podem controlar todo o ciclo de vida das encomendas e os usuários finais podem acompanhar suas entregas de forma prática e intuitiva. O projeto aplica conceitos fundamentais de programação orientada a objetos, manipulação de banco de dados e desenvolvimento de interfaces gráficas.

## 🚀 Funcionalidades

### Painel Administrativo

- **Login e Cadastro de Administradores**: Sistema de autenticação seguro para acesso ao painel
- **Gestão de Encomendas**:
  - Cadastrar novas encomendas
  - Editar informações de encomendas existentes
  - Remover encomendas do sistema
  - Visualizar lista completa de encomendas
  - Atualizar status de entrega

### Sistema de Rastreio

- **Consulta Pública**: Rastreamento de encomendas através de código único
- **Visualização de Status**: Informações detalhadas sobre localização e etapa da entrega
- **Interface Intuitiva**: Experiência simplificada para o usuário final

## 💻 Tecnologias Utilizadas

### Linguagem e Framework

- **Java**: Linguagem principal do projeto, utilizando paradigma orientado a objetos
- **Swing/JOptionPane**: Biblioteca Java para criação de interfaces gráficas desktop com diálogos interativos

### Banco de Dados

- **MySQL**: Sistema de gerenciamento de banco de dados relacional para armazenamento persistente
- **JDBC**: API Java para conexão e manipulação do banco de dados MySQL

### Ferramentas de Desenvolvimento

- **JDK (Java Development Kit)**: Kit de desenvolvimento Java
- **IDE**: Ambiente de desenvolvimento integrado (NetBeans, Eclipse ou IntelliJ IDEA)
- **Git**: Controle de versão do código-fonte

## 📋 Estrutura do Sistema

```
JavaParcel/
├── src/
│   ├── models/          # Classes de modelo (Encomenda, Usuário, etc.)
│   ├── controllers/     # Lógica de negócio e controle
│   ├── views/           # Interfaces gráficas (JOptionPane)
│   ├── database/        # Conexão e operações com MySQL
│   └── utils/           # Classes utilitárias
├── database/
│   └── schema.sql       # Script de criação do banco de dados
└── README.md
```

## 🔧 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java JDK 8** ou superior
- **MySQL Server 5.7** ou superior
- **MySQL Connector/J** (driver JDBC)
- Uma IDE Java de sua preferência

## ⚙️ Configuração e Instalação

### 1. Configurar o Banco de Dados

```sql
-- Criar o banco de dados
CREATE DATABASE javaparcel;

-- Usar o banco de dados
USE javaparcel;

-- As tabelas serão criadas automaticamente pelo sistema
-- ou execute o script schema.sql se disponível
```

### 2. Configurar Conexão com o Banco

Edite as configurações de conexão no arquivo de configuração do projeto com suas credenciais do MySQL:

```java
String url = "jdbc:mysql://localhost:3306/javaparcel";
String usuario = "root";
String senha = "sua_senha";
```

### 3. Compilar e Executar

```bash
# Compilar o projeto
javac -d bin src/**/*.java

# Executar o sistema
java -cp bin Main
```

Ou utilize sua IDE para compilar e executar o projeto diretamente.

## 📱 Como Usar

### Para Administradores:

1. Execute o sistema e selecione "Painel Administrativo"
2. Faça login com suas credenciais (ou cadastre-se se for o primeiro acesso)
3. Utilize o menu para gerenciar encomendas:
   - Cadastrar nova encomenda com dados do destinatário
   - Atualizar status de encomendas existentes
   - Consultar e editar informações

### Para Usuários:

1. Execute o sistema e selecione "Rastreamento de Encomendas"
2. Informe o código de rastreamento da encomenda
3. Visualize as informações e status atual da entrega

## 👥 Equipe

Projeto desenvolvido por alunos do 2º Semestre da Universidade São Judas como parte da Atividade A3.

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos como parte da Atividade A3 da Universidade São Judas.

## 📞 Suporte

Para dúvidas ou problemas relacionados ao projeto, entre em contato com o professor responsável pela disciplina de Programação de Soluções Computacionais.

---

**Universidade São Judas** | 2º Semestre - 2025 | Programação de Soluções Computacionais
