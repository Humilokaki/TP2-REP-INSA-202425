FROM eclipse-temurin:21 AS runtime

WORKDIR /app

COPY . .

RUN javac FloatingPointAssociativityWithError.java main/PropertyChecker.java

CMD ["sh", "-c", "java FloatingPointAssociativityWithError && java main/PropertyChecker"]
