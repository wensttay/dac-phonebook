# dac-phonebook
Uma simples agenda telefônica fazendo uso de tecnologias aprendidas em sala, como: JSF, EJB3 e Docker, desenvolvida como exercício de avaliação para a cadeira de Desenvolvimento de Aplicações Corporativas (DAC) do Curso de Análise e Desenvolvimento de Sistemas (ADS) do IFPB campus Cajazeiras.

## Requisitos Funcionais

- **RF01**: Pesquisar um contato pelo seu nome.
- **RF02**: Listar os contatos por ordem alfabética e agrupados pela primeira letra de seu nome.
- **RF03**: Cadastrar um novo contato.
- **RF04**: Editar um contato existente.
- **RF05**: Excluir um contato existente

## Pré-requisitos de Implantação
Para efetuar a implantação da aplicação, se faz necessário ter instalado e configurado as seguintes tecnologias:

- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [JDK](http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html)
- [Maven](http://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/engine/installation/)
- [Docker Compose](https://docs.docker.com/compose/install/) (Opcional)

## Manual de Implantação
**Após instalar e configurar as ferramentas descritas em 'Pré-requisitos de Implantação'**, precisamos efetuar o download do código fonte do projeto para a sua máquina, e para isso, basta seguir os passos a seguir:

1. Escolha uma pasta qualquer onde deseja armazenar os arquivos do projeto.
2. Com o Terminal aberto navegue até o local escolhido (usando comandos como o "cp [path_do_local]" no linux).
3. Execute o comando `git clone https://github.com/wensttay/dac-phonebook.git`.
4. Após o termino da execução do comando anterior, o sistema deverá ter criado uma pasta com o nome "dac-phonebook" no diretorio escolhido;
5. Ainda no terminal adentre a pasta "dac-phonebook".
6. Siga para uma das formas de implantação descritas a seguir:

Visando facilitar a implantação desse projeto, o mesmo disponibiliza três formas de executar sua implantação descritas a seguir do mais simples para o de mais complexa (porém mais configuravel) execusão:

#### Usando Bourne Shell (.sh) no Linux 
Execute o seguinte comando **dentro do diretorio raiz do projeto**:

1. `sudo sh start.sh`

#### Usando o Docker Compose
Execute os seguintes comandos **dentro do diretorio raiz do projeto**:

1. `mvn clean install`
2. `docker-compose up -d --build`

#### Usando Dockerfiles 
Execute os seguintes comandos **dentro do diretorio raiz do projeto**:

1. `mvn clean install`
2. `docker build -t dac-phonebook-core-db ./dac-phonebook-core/src/main/resources`
3. `docker build -t dac-phonebook-core ./dac-phonebook-core`
4. `docker build -t dac-phonebook-web ./dac-phonebook--web`
5. `docker run -p 5433:5432 -d --name dac-phonebook-core-db dac-contacts-core-db`
6. `docker run -p 8081:8080 -p 1098:1099 -p 1097:1098 -p 3874:3873 -p 3701:3700 -p 8182:8181 -p 8010:8009 -d --name dac-phonebook-core --link dac-phonebook-core-db:dac-phonebook-core-db dac-phonebook-core`
7. `docker run -p 8082:8080 -d --name dac-phonebook-web --link dac-phonebook-core:dac-phonebook-core dac-phonebook-web`

Independente de qual das três maneiras de implantar for executada, **caso não tenha a nécessidade de alterar as portas definidas nos comandos desctritos**, a aplicação estará disponivel para acesso em: [http://localhost:8082/dac-phonebook-web/](http://localhost:8082/dac-phonebook-web/ target="_blank")
