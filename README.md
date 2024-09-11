# Template Automation

**Template Automation** é um template para automação de testes web utilizando tecnologias e ferramentas populares, visando eficiência e organização na criação de testes automatizados.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Cucumber**: Para escrita de testes em formato BDD.
- **JUnit**: Framework de testes.
- **Selenium**: Biblioteca para automação de navegadores.
- **Apache PDFBox**: Manipulação de arquivos PDF.
- **Log4j2**: Framework de logging.
- **Lombok**: Biblioteca para redução de código boilerplate.
- **Bonigarcia Selenium**: Gerenciamento de versões do WebDriver.

## Estrutura do Projeto

O projeto segue o padrão **Page Object** para organização dos testes. A estrutura de diretórios é organizada da seguinte forma:

- **`src/main/resources`**: Contém:
  - **`features`**: Diretório onde os arquivos `.feature` são armazenados.
  - **`evidences`**: Diretório onde as evidências geradas em formato `.pdf` serão armazenadas.

- **`src/main/java`**: Contém o código principal da aplicação e dos testes.

- **`selenium/{nome_do_projeto}`**: Diretório onde as classes de steps devem ser criadas. Utilize o modelo encontrado em `selenium/saucedemo` como referência. Este diretório possui três pacotes:
  - **`logics`**: Contém a lógica de execução dos testes.
  - **`steps`**: Contém as classes de steps do Cucumber.
  - **`pages`**: Contém as páginas do padrão Page Object.

## Configuração e Execução

1. **Clonar o Repositório**

   ```sh
   git clone <URL_DO_REPOSITORIO>
   cd template_automation

## Configuração e Execução

2. **Configurar Dependências**

   Certifique-se de que o seu ambiente de desenvolvimento possui o [Maven](https://maven.apache.org/) ou [Gradle](https://gradle.org/), pois essas ferramentas são usadas para gerenciar as dependências do projeto.

   **Para Maven**:
   ```sh
   mvn clean install

3. **Executar os Testes**

   Para executar os testes, você deve rodar a classe `WebRunner` que está localizada em `src/test/java`. Esta classe irá invocar os arquivos `.feature` presentes em `src/main/resources/features`.

   **Para Maven**:
   ```sh
   mvn test
## Adicionando Novos Testes

Para adicionar novos testes, siga os passos abaixo:

1. **Criar um Novo Arquivo `.feature`**

   Coloque o novo arquivo na pasta `src/main/resources/features`.

2. **Criar Classes de Steps**

   Crie as classes de steps na pasta `src/test/java/selenium/{nome_do_projeto}/steps`, utilizando os pacotes `logics`, `steps` e `pages` como referência. Lembre-se de que cada página web utilizada deve ter uma classe correspondente de steps, logic e page. Além disso, os steps devem corresponder aos steps definidos em seu arquivo `.feature`.

## Autores

- [Ryan Lumora](https://github.com/ryanlumora/template_automation)
