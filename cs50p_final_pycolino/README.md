# Pycolino

## Description:
Pycolino is a simple command-line tool for adding notes to your files and reading them. By default, without any arguments, 
Pycolino will display the complete directory along with any existing notes associated with the files.

## Features

* **Read Mode:** View the notes associated with a specific file.
* **Write Mode:** Add or overwrite notes for a specific file.
* **Default Mode:** Display the complete directory along with any associated messages.

## Installation

To install Pycolino, follow these steps:

1) Clone the entire repository as described in the readme of the main directory

2) Navigate to the project directory:
    ```bash
    cd pycolino
    ```
3) Install the required depedncies using pip:
    ```bash
    pip install -r requirements.txt
    ```
## Usage

### Command-Line Arguments

* `-r, --read`: Specify the name of the file whose notes you want to read. If you use this option, the `-w, --write` option must not be selected.
* `-w, --write`: Write a note (in quotes) about the file you select with `-r`.

### Using Pycolino in any directory
* To use pycolino in any directory, pycolino.py only needs to be copied into the respective directory. On first use, a pycolino.csv is created to ensure functionality.

### Examples

1) To read the notes for a file named `example.txt`, run:

    ```bash
    python pycolino.py -r example.txt
    ```

2) To add or overwrite notes for the same file, run:

    ```bash
    python pycolino.py -r example.txt -w "This is an example note."
    ```
3) To show the complete directory along with any associated messages, run:

   ```bash
    python pycolino.py
    ```

## File Structure

* `pycolino.py`: The main Python script containing the Pycolino command-line tool.
* `pycolino.csv`: The CSV file used for storing file names and associated notes.
* `test_pycolino.py`: A set of unit tests to ensure the functionality
  
## Testing

Pycolino includes a set of unit tests to ensure its functionality. To run the tests, use the following command:
   ```bash
   pytest
   ```

## Author

* Author: Christian Grimm

