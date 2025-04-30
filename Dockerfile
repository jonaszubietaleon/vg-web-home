# Usamos una imagen base con Java (Temurin es la distribución de OpenJDK)
FROM eclipse-temurin:17-jdk AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos todo el contenido del proyecto en el contenedor
COPY . .

# Aseguramos que el script mvnw tenga permisos de ejecución
RUN chmod +x ./mvnw

# Ejecutamos Maven para descargar las dependencias sin hacer tests (solo para hacer offline)
RUN ./mvnw dependency:go-offline

# Ejecutamos Maven para compilar el proyecto y crear el archivo JAR (sin tests)
RUN ./mvnw clean package -DskipTests

# Usamos otra imagen base para correr la aplicación (en este caso, una de OpenJDK sin Maven)
FROM eclipse-temurin:17-jre

# Directorio de trabajo en el contenedor final
WORKDIR /app

# Copiamos el archivo JAR generado desde la etapa anterior
COPY --from=build /app/target/*.jar /app/app.jar

# Exponemos el puerto 8080
EXPOSE 8080

# Comando para ejecutar el archivo JAR
CMD ["java", "-jar", "vg-ms-casas.jar"]
