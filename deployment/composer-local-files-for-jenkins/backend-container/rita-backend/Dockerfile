FROM adoptopenjdk/openjdk11:jre11u-centos-nightly

EXPOSE 15502

ARG JAR_FILE=sofia-0.0.1.jar
ADD ${JAR_FILE} app.jar

ARG DB_IP
ARG DB_PORT
ARG DB_NAME
ARG DB_USERNAME
ARG DB_PASSWORD
ARG KEYCLOAK_URL
ARG KEYCLOAK_CLIENT_NAME
ARG KEYCLOAK_CLIENT_ID
ARG KEYCLOAK_CLIENT_SECRET

ENV DB_IP_ENV=${DB_IP}
ENV DB_PORT_ENV=${DB_PORT}
ENV DB_NAME_ENV=${DB_NAME}
ENV DB_USERNAME_ENV=${DB_USERNAME}
ENV DB_PASSWORD_ENV=${DB_PASSWORD}

ENV KEYCLOAK_URL_ENV=${KEYCLOAK_URL}
ENV KEYCLOAK_CLIENT_NAME_ENV=${KEYCLOAK_CLIENT_NAME}
ENV KEYCLOAK_CLIENT_ID_ENV=${KEYCLOAK_CLIENT_ID}
ENV KEYCLOAK_CLIENT_SECRET_ENV=${KEYCLOAK_CLIENT_SECRET}

RUN touch ./entrypoint.sh
RUN echo "java -jar ./app.jar --spring.profiles.active=prod --sofia.db.ip=${DB_IP_ENV} --sofia.db.port=${DB_PORT_ENV} --sofia.db.name=${DB_NAME_ENV} --sofia.db.username=${DB_USERNAME_ENV} --sofia.db.password=${DB_PASSWORD_ENV}  --keycloak.url=${KEYCLOAK_URL_ENV} --keycloak.client-name=${KEYCLOAK_CLIENT_NAME_ENV} --keycloak.client-id=${KEYCLOAK_CLIENT_ID_ENV} --keycloak.client-secret=${KEYCLOAK_CLIENT_SECRET_ENV}" > ./entrypoint.sh
RUN chmod +x ./entrypoint.sh
CMD ./entrypoint.sh
