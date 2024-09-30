# Etapa 1: Build (compilar o projeto usando Maven)
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo pom.xml e as dependências do projeto para o cache Maven
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o código-fonte do projeto
COPY src ./src

# Compila o projeto e cria o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Runtime (executar o JAR no ambiente final)
FROM eclipse-temurin:17-jre

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o JAR da etapa anterior para o contêiner
COPY --from=build /app/target/*.jar app.jar

# Expondo a porta que o serviço vai utilizar
EXPOSE 8081

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
