import random
import pandas as pd

def check_property(operation1, operation2, repetitions, precision):
    """
    Check if the given floating-point operations yield similar results 
    within a specified precision across a number of repetitions.
    """
    correct_count = 0
    format_str = f".{precision}f"
    
    for _ in range(repetitions):
        x, y, z = random.random(), random.random(), random.random()
        
        # Format the numbers to the specified precision
        x = float(format(x, format_str))
        y = float(format(y, format_str))
        z = float(format(z, format_str))
        
        try:
            # Evaluate the operations
            result1 = eval(operation1)
            result2 = eval(operation2)
            
            if result1 == result2:
                correct_count += 1
        except ZeroDivisionError:
            # Handle division by zero
            print(f"Division by zero encountered with x={x}, y={y}, z={z}. Skipping this iteration.")
            continue

    success_rate = (correct_count / repetitions) * 100
    print(f"Out of {repetitions} trials, {success_rate:.2f}% of the time "
          f"the property held for {operation1} and {operation2}.")
    return {
        "operation1": operation1,
        "operation2": operation2,
        "repetitions": repetitions,
        "precision": precision,
        "success_rate": success_rate
    }

# Define possible combinations of operations and repetition counts
operations = [
    {"operation1": "(x + y) + z", "operation2": "x + (y + z)"},  # Associativity
    {"operation1": "x + y", "operation2": "y + x"},              # Commutativity
    {"operation1": "(x * y) * z", "operation2": "x * (y * z)"},  # Multiplicative Associativity
    {"operation1": "x * (y / z)", "operation2": "(x * y) / z"}   # Combination of operations
]

# Define different repetition counts and precision levels
repetitions_list = [250, 500, 1000, 2000]
precision_levels = [0, 2, 5, 7, 9, 12]

# Loop through all combinations of operations and repetitions
results = []
for op in operations:
    for reps in repetitions_list:
        for prec in precision_levels:
            print(f"\nChecking {op['operation1']} vs {op['operation2']} "
                  f"with {reps} repetitions and precision {prec}:")
            result = check_property(op['operation1'], op['operation2'], reps, prec)
            results.append(result)

# Save the results to a CSV for analysis
df = pd.DataFrame(results)
df.to_csv("variability_results.csv", index=False)
print("\nResults have been saved to 'variability_results.csv'")
