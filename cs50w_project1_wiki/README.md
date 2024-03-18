# Wiki

# Description
Wiki app is a Django web application designed to function as an online encyclopedia. It allows users to view, search, create, edit, and explore various encyclopedia entries.

## Features
- **View Entries:** Users can browse through a list of encyclopedia entries on the homepage.
- **Search:** A search functionality is provided to search for specific entries.
- **Create Entry:** Users can create new encyclopedia entries.
- **Edit Entry:** Existing entries can be edited by users.
- **Random Entry:** Users can view a random encyclopedia entry.

## Installation
To run the app locally, follow these steps:

1) Clone the entire repository as described in the readme of the main directory

2) Navigate to the project directory:
   ```bash
   cd application_portfolio/cs50w_project1_wiki
   ```
3) Install the required dependencies:
   ```bash
   pip install -r requirements.txt
   ```
4) Apply database migrations
   ```bash
   python manage.py migrate
   ```
5) Start the development server:
   ```bash
   python manage.py runserver
   ```
6) Access the application in your browser at 'http://localhost:8000'

## Usage
* View Entries: Visit the homepage or home to view a list of encyclopedia entries.
* Search: Use the search bar to find specific entries by title.
* View Entry Content: Click on an entry title to view its content.
* Create Entry: Click on "Create New Page" to create a new encyclopedia entry.
* Edit Entry: Click on "Edit" next to an entry to edit its content.
* Random Entry: Click on "Random Page" to view a random encyclopedia entry.

## File structure
* encyclopedia/: Contains Django app files, including views, templates, and static files.
* entries/: Directory to store encyclopedia entries in Markdown format.

## Dependencies
* Django: Python web framework for building web applications.
* Markdown2: A Python library for converting Markdown to HTML.

## Contributing
Contributions are welcome! If you find any bugs or have suggestions for improvement, please submit a pull request on my GitHub repository.

## Author
Author: Christian Grimm

