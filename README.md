# Settings Manager

Um sistema para gerenciar configurações de ambientes com suporte a ativação/desativação e controle de versões.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework para desenvolvimento rápido de aplicações Java.
- **MongoDB**: Banco de dados NoSQL utilizado para armazenar as configurações.
- **Maven**: Gerenciador de dependências e build.

## Funcionalidades

- Criar novas configurações.
- Atualizar configurações existentes.
- Ativar ou desativar configurações.
- Buscar configurações ativas ou inativas por chave e ambiente.
- Listar todas as configurações de um ambiente.

## Endpoints

### Configurações
- **POST /config**: Criar uma nova configuração.
- **PUT /config/{id}**: Atualizar uma configuração existente.
- **PUT /config/{id}/toggle**: Ativar ou desativar uma configuração.
- **GET /config/{key}/{environment}/active**: Buscar uma configuração ativa.
- **GET /config/{key}/{environment}/inactive**: Buscar uma configuração inativa.
- **GET /config/{environment}**: Listar todas as configurações de um ambiente.

## Como Executar o Projeto

1. Certifique-se de ter o **Java 17+** e o **Maven** instalados.
2. Clone o repositório:
   ```bash
   git clone https://github.com/kevinwfernandes/settings-manager.git