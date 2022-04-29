FROM adoptopenjdk/openjdk11:jre11u-centos-nightly

EXPOSE 15502

ARG JAR_FILE=sofia-0.0.1.jar
ADD ${JAR_FILE} app.jar

ARG SOFIA_IMPORTS_FOLDER=sofia_imports
ADD ${SOFIA_IMPORTS_FOLDER} sofia_imports
