import subprocess

def send_input_to_script(input_data):
    # Start the subprocess
    process = subprocess.Popen(
        ['python', 'my_script.py'],  # The script to run
        stdin=subprocess.PIPE,      # Open stdin pipe
        stdout=subprocess.PIPE,     # Open stdout pipe
        stderr=subprocess.PIPE,     # Open stderr pipe
        text=True                    # Treat input and output as text
    )

    # Write input_data to the stdin of the subprocess
    stdout, stderr = process.communicate(input=input_data)

    # Print the output and errors
    print("Output from receiver.py:")
    print(stdout)
    if stderr:
        print("Errors:")
        print(stderr)

