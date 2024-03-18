# Use a imagem de node como base
FROM node:14 as build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo package.json para o diretório de trabalho
COPY package.json package-lock.json ./

# Instale as dependências
RUN npm install

# Copie os arquivos do projeto para o diretório de trabalho
COPY . .

# Execute o build do Angular
RUN npm run build

# Multi-stage build para criar uma imagem mais leve
FROM nginx:alpine

# Copie os arquivos de build do Angular para o diretório de trabalho do nginx
COPY --from=build /app/dist/* /usr/share/nginx/html/

# Exponha a porta 80
EXPOSE 80

# Comando para iniciar o nginx
CMD ["nginx", "-g", "daemon off;"]
