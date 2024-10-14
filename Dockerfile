FROM eclipse-temurin:21

WORKDIR /app

COPY . /app

RUN javac FloatingPointAssociativityWithError.java

CMD ["java", "FloatingPointAssociativityWithError"]
