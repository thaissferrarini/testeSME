# Estágio de construção
FROM node:16 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o package.json e o package-lock.json para o diretório de trabalho
COPY package.json package-lock.json ./

# Instala as dependências do projeto
RUN npm install

# Copia todos os arquivos do projeto para o diretório de trabalho
COPY . .

# Executa o comando de build
RUN npm run build --prod

# Estágio de produção
FROM nginx:alpine

# Copia os arquivos de build do estágio de construção para o diretório de Nginx
COPY --from=build /app/dist/cadastro-pessoa /usr/share/nginx/html

# Expondo a porta 80 para tráfego HTTP
EXPOSE 80

# Comando para iniciar o servidor Nginx em segundo plano
CMD ["nginx", "-g", "daemon off;"]
