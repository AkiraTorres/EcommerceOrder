# Usando a imagem oficial do OpenJDK 21
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo JAR gerado pelo Maven para o contêiner
COPY target/*.jar app.jar

# Porta a ser exposta (lembrar que cada serviço usará uma porta diferente)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
