import random

def check_property(repetitions, data_type):
    correct_count = 0
    
    for _ in range(repetitions):
        x = random.random() * 100
        y = random.random() * 100
        z = random.random() * 100

        if data_type == "int":
            x = int(x)
            y = int(y)
            z = int(z)

        # Check if the associative property holds: (x + y) + z == x + (y + z) for addition
        # or (x * y) * z == x * (y * z) for multiplication
        result1 = (x + y) + z  # Change this for different operations
        result2 = x + (y + z)  # Change this for different operations

        if result1 == result2:
            correct_count += 1

    result = correct_count / repetitions * 100
    print(f"Correctness percentage: {result:.2f}%")

# Define the number of repetitions and data type
repetitions = 10000  # Change this as needed
data_type = "float"  # Change to "int" if you want integers
check_property(repetitions, data_type)
