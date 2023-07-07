FROM openjdk:19

RUN microdnf install findutils

WORKDIR /lock

COPY . .

RUN keytool -import -trustcacerts -file nscacert.pem -cacerts -storepass changeit -alias ca -noprompt
