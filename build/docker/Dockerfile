FROM openjdk:8

# non root/user setup
RUN mkdir -p /home/app_user
RUN groupadd -r app_user &&\
    useradd -r -g app_user -d /home/app_user -s /sbin/nologin -c "Docker image user" app_user
ENV HOME=/home/app_user
WORKDIR $HOME
COPY web/target/web-0.0.1-SNAPSHOT.jar /$HOME/app.jar
RUN mkdir -p $HOME/.seamless/sources
RUN chown -R app_user:app_user $HOME
USER app_user

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
