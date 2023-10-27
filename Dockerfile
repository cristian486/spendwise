FROM eclipse-temurin:17-alpine
WORKDIR /spendwise/
COPY target/*.jar spendwise.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENV DATABASE_URL=jdbc:mysql://database:3306/spendwise
ENV DATABASE_USERNAME=root
ENV DATABASE_PASS=ZGF0YWJhc2VwYXNzd29yZAo=
ENV ISSUER_URI=http://keycloak:8080/realms/spendwise
ENV JWK_URI=http://keycloak:8080/realms/spendwise/protocol/openid-connect/certs
ENV KEYCLOAK_URL=http://keycloak:8080
ENV KEYCLOAK_ADMIN_USER=admin
ENV KEYCLOAK_ADMIN_PASSWORD=VW00UzNOaEFGMHJUM1ByMEtFWUNsMDRrCg==
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spendwise.jar"]

