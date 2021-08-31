FROM adoptopenjdk/openjdk11:jre11u-centos-nightly

EXPOSE 15502

ARG JAR_FILE=sofia-0.0.1.jar
ADD ${JAR_FILE} app.jar

ARG DB_IP
ARG DB_PORT
ARG DB_NAME
ARG DB_USERNAME
ARG DB_PASSWORD

ENV DB_IP_ENV=${DB_IP}
ENV DB_PORT_ENV=${DB_PORT}
ENV DB_NAME_ENV=${DB_NAME}
ENV DB_USERNAME_ENV=${DB_USERNAME}
ENV DB_PASSWORD_ENV=${DB_PASSWORD}

RUN touch ./entrypoint.sh
RUN echo "java -jar ./app.jar --spring.profiles.active=prod --sofia.db.ip=${DB_IP_ENV} --sofia.db.port=${DB_PORT_ENV} --sofia.db.name=${DB_NAME_ENV} --sofia.db.username=${DB_USERNAME_ENV} --sofia.db.password=${DB_PASSWORD_ENV}" > ./entrypoint.sh
RUN chmod +x ./entrypoint.sh
CMD ./entrypoint.sh
