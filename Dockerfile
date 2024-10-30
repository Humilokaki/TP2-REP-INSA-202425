FROM eclipse-temurin:21

WORKDIR /app

COPY . /app

# Install Python 3.10.12 and pip
RUN apt-get update && \
    apt-get install -y software-properties-common && \
    add-apt-repository ppa:deadsnakes/ppa && \
    apt-get update && \
    apt-get install -y python3.10 python3.10-distutils && \
    curl -sS https://bootstrap.pypa.io/get-pip.py | python3.10

COPY requirements.txt .
RUN if [ -f requirements.txt ]; then pip install -r requirements.txt; fi

RUN javac FloatingPointAssociativityWithError.java

# Run both Java and Python programs sequentially
CMD java FloatingPointAssociativityWithError && python3 your_script.py
