# Projeto Java e Angular com Docker

Este é um projeto que combina uma aplicação back-end em Java e uma aplicação front-end em Angular, ambos contidos em contêineres Docker separados.

## Pré-requisitos

Certifique-se de ter o Docker instalado em sua máquina. Você pode baixá-lo em [Docker Hub](https://hub.docker.com/).

## Rodando o projeto

Siga as etapas abaixo para rodar o projeto:

### 1. Clone o repositório

git clone https://github.com/thaissferrarini/testeSME

### 2. Inicie os contêineres

Navegue até o diretório raiz do projeto:

cd testeSME

Execute o Docker Compose para iniciar os contêineres:

docker-compose up

Isso iniciará os contêineres do projeto Java e Angular. O front-end em `http://localhost:80` e o back-end está disponível `http://localhost:8080/swagger-ui.html/`.

## Parando os contêineres

Para parar os contêineres, `docker-compose down`.
