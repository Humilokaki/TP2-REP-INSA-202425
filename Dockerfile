# Start with the Python 3.10 image and install Java in the same stage
FROM python:3.10-slim AS runtime

# Install Java (OpenJDK)
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy application files to the container
COPY . /app

# Compile the Java program
RUN javac FloatingPointAssociativityWithError.java

# Install Python dependencies if a requirements.txt is present
COPY requirements.txt .  
RUN if [ -f requirements.txt ]; then pip install -r requirements.txt; fi

# Run the Java program followed by the Python script (no output expected from Python)
CMD java FloatingPointAssociativityWithError && python3 main/explore_variability.py
