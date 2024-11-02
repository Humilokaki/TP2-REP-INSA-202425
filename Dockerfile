FROM eclipse-temurin:21 AS runtime

WORKDIR /app

COPY . .

RUN javac FloatingPointAssociativityWithError.java PropertyChecker.java

CMD ["sh", "-c", "java FloatingPointAssociativityWithError && java PropertyChecker"]
