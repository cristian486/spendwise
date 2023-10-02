FROM eclipse-temurin:17-alpine
WORKDIR /spendwise/
COPY target/*.jar spendwise.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENV DATABASE_URL=jdbc:mysql://database:3306/spendwise
ENV DATABASE_USERNAME=root
ENV DATABASE_PASS=ZGF0YWJhc2VwYXNzd29yZAo=
ENV DATABASE_DRIVE=com.mysql.cj.jdbc.Driver
ENV ISSUER_URI=http://keycloak:8080/realms/spendwise
ENV JWK_URI=http://keycloak:8080/realms/spendwise/protocol/openid-connect/certs
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spendwise.jar"]

