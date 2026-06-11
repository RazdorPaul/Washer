FROM gradle:8.10-jdk21

WORKDIR /Washer

COPY . .

RUN ["./gradlew", "clean", "build"]

CMD ["./gradlew", "run"]