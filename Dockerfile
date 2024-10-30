# Stage 1: Build and run the Java application
FROM eclipse-temurin:21 AS java-stage

WORKDIR /app

COPY . /app

# Compile the Java program
RUN javac FloatingPointAssociativityWithError.java

# Stage 2: Python environment for running Python script and dependencies
FROM python:3.10-slim AS python-stage

WORKDIR /app

# Copy all files from the previous stage into this one
COPY --from=java-stage /app /app

COPY requirements.txt .  # Only if you have a requirements.txt
RUN if [ -f requirements.txt ]; then pip install -r requirements.txt; fi

# Default command to first run Java then Python
CMD ["sh", "-c", "java FloatingPointAssociativityWithError && python3 main/explore_variability.py"]
