# Spring AI Chat com Vaadin e Gemini

Este é um projeto de exemplo que demonstra a criação de um aplicativo de chat em tempo real utilizando o poder do Spring Boot com o Spring AI. A interface do usuário é construída com Vaadin, e a inteligência artificial é fornecida pelo modelo Gemini do Google Cloud Vertex AI.

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![Vaadin](https://img.shields.io/badge/Vaadin-24.x-cyan)
![Google Cloud](https://img.shields.io/badge/Google%20Cloud-Vertex%20AI-orange)

## 📜 Descrição

A aplicação oferece uma interface de chat simples onde o usuário pode enviar uma mensagem e receber uma resposta gerada pelo modelo de linguagem Gemini 1.5 Flash. O projeto é um excelente ponto de partida para explorar as capacidades do Spring AI em um ambiente web reativo com Vaadin.

*(Você pode adicionar um screenshot da sua aplicação aqui)*
`![Screenshot do Chat](caminho/para/seu/screenshot.png)`

## ✨ Funcionalidades

-   **Interface de Chat Reativa:** Construída com Vaadin para uma experiência de usuário fluida e sem a necessidade de escrever JavaScript.
-   **Integração com IA Generativa:** Conecta-se diretamente ao modelo Gemini do Google Vertex AI.
-   **Configuração Simplificada:** Utiliza o sistema de propriedades do Spring Boot para gerenciar as configurações da API.
-   **Tratamento de Autenticação:** Demonstra os métodos necessários para autenticar com o Google Cloud em um ambiente Spring.

## 🛠️ Tecnologias Utilizadas

-   **Backend:**
    -   Java 17
    -   Spring Boot 3
    -   Spring AI
    -   Spring Cloud GCP
-   **Frontend:**
    -   Vaadin 24
-   **Plataforma de IA:**
    -   Google Cloud Vertex AI (Gemini)
-   **Build:**
    -   Maven (ou Gradle)

## ⚙️ Configuração do Ambiente

Para executar este projeto, você precisará configurar tanto o ambiente de desenvolvimento local quanto o projeto no Google Cloud. Siga os passos cuidadosamente.

### Pré-requisitos Locais

1.  **JDK 17 ou superior:** [Instale o Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
2.  **Maven 3.8+ ou Gradle 7.5+:** Ferramenta para gerenciar as dependências e o build do projeto.
3.  **Google Cloud SDK (gcloud CLI):** Essencial para a autenticação. [Siga as instruções de instalação](https://cloud.google.com/sdk/docs/install).

### Configuração do Projeto Google Cloud

Esta é a parte mais crítica. A aplicação não funcionará sem estes passos.

#### 1. Crie ou Selecione um Projeto
Acesse o [Console do Google Cloud](https://console.cloud.google.com/) e crie um novo projeto ou selecione um existente. O ID do seu projeto será necessário.

#### 2. Ative a Faturamento (Billing)
Serviços de IA Generativa exigem que o faturamento esteja ativo no projeto.
-   No menu de navegação (☰), vá para **Faturamento**.
-   Verifique se o seu projeto está vinculado a uma conta de faturamento ativa.

#### 3. Ative a API Vertex AI
A aplicação precisa de permissão para usar o serviço.
-   No menu de navegação (☰), vá para **APIs e Serviços > Biblioteca**.
-   Procure por **"Vertex AI API"**.
-   Clique em **ATIVAR**.

#### 4. Configure a Autenticação
Você tem duas opções. A **Opção A é a mais recomendada** para desenvolvimento local.

**Opção A: Application Default Credentials (ADC) - RECOMENDADO**

Este método usa a CLI `gcloud` para autenticar sua máquina. É mais simples e seguro.

1.  No seu terminal, execute o comando:
    ```bash
    gcloud auth application-default login
    ```
2.  Uma janela do navegador será aberta para você fazer login com sua conta do Google.
3.  Pronto! Sua aplicação encontrará as credenciais automaticamente. Deixe as propriedades de credenciais no `application.properties` comentadas.

**Opção B: Chave de Conta de Serviço (Arquivo JSON)**

Use este método se não puder usar o ADC.

1.  **Crie uma Conta de Serviço:**
    -   No console do Google Cloud, vá para **IAM e admin > Contas de Serviço**.
    -   Clique em **CRIAR CONTA DE SERVIÇO**. Dê um nome a ela.
    -   **Conceda Permissões:** Conceda a esta conta o papel de **"Usuário de Vertex AI" (Vertex AI User)**.
    -   Clique em **CONCLUÍDO**.

2.  **Crie e Baixe a Chave JSON:**
    -   Encontre a conta de serviço que você criou na lista.
    -   Clique nos três pontos (Ações) e vá para **Gerenciar chaves**.
    -   Clique em **ADICIONAR CHAVE > Criar nova chave**.
    -   Escolha **JSON** e clique em **CRIAR**. Um arquivo `.json` será baixado. Guarde-o em um local seguro.

### Configuração do `application.properties`

Abra o arquivo `src/main/resources/application.properties` e configure-o de acordo com o método de autenticação escolhido.

```properties
# Nome da Aplicação
spring.application.name=SpringAIChat

# --- Configurações do Spring AI para Vertex AI Gemini ---
# Substitua pelo ID do seu projeto no Google Cloud
spring.ai.vertex.ai.gemini.project-id=seu-id-de-projeto-aqui
spring.ai.vertex.ai.gemini.location=us-central1
spring.ai.vertex.ai.gemini.model=gemini-1.5-flash-001

# --- Configurações de Credenciais ---
# Se você usou a Opção A (ADC), deixe estas linhas comentadas ou apague-as.
# Se você usou a Opção B (Arquivo JSON), descomente as linhas abaixo e
# aponte para o caminho ONDE VOCÊ SALVOU o arquivo JSON.

# 1. Para o Spring AI
#spring.ai.vertex.ai.gemini.credentials-uri=file:/caminho/completo/para/seu/arquivo.json

# 2. Para o Spring Cloud GCP (necessário para compatibilidade)
#spring.cloud.gcp.credentials.location=file:/caminho/completo/para/seu/arquivo.json


# --- Outras Configurações ---
# Desativa o endpoint de chat REST padrão do Spring AI, pois usamos Vaadin
spring.ai.chat.controller.enabled=false

# Porta do servidor
server.port=8085
```

### 📄. Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para detalhes.

### 📄. Contato

    -  Desenvolvedor: Leandro Nazareth
    -  Email: leandrosnazareth@gmail.com
    -  LinkedIn: https://www.linkedin.com/in/leandrosnazareth
