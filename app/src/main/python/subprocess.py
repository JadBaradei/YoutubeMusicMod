import threading
import subprocess
import sys

def run_script(input_data):
    process = subprocess.Popen(
        [sys.executable, "main/python/main.py", "setup_oauth"],  # Pass the function name as an argument
        stdin=subprocess.PIPE,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True
    )
    stdout, stderr = process.communicate(input=input_data)
    if stderr:
        print(f"Error: {stderr}")

# Paths to your Python scripts
script1_path = '/path/to/script1.py'
script2_path = '/path/to/script2.py'

# Create threads for specific functions in each script
thread1 = threading.Thread(target=run_script, args=(script1_path, 'function1'))
thread2 = threading.Thread(target=run_script, args=(script2_path, 'function2'))

# Start threads
thread1.start()
thread2.start()

# Wait for both threads to finish
thread1.join()
thread2.join()
