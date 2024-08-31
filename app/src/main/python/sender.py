import subprocess
import sys

def send_input_to_script(input_data):
    # Start the subprocess
    process = subprocess.Popen(
    [sys.executable, 'main/python/main.py'],
    stdin=subprocess.PIPE,
    stdout=subprocess.PIPE,
    stderr=subprocess.PIPE,
    text=True
    )

    # Write input_data to the stdin of the subprocess
    stdout, stderr = process.communicate(input=input_data)

    # Print the output and errors
    print("Output from receiver.py:")
    print(stdout)
    return "Successfull"
    if stderr:
        print("Errors:")
        print(stderr)


