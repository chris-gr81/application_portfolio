# Wiki

# Description
Wiki App is a Django web application designed to function as an online encyclopedia. It allows users to view, search, create, edit, and explore various encyclopedia entries.

## Features
- **View Entries:** Users can browse through a list of encyclopedia entries on the homepage.
- **Search:** A search functionality is provided to search for specific entries.
- **Create Entry:** Users can create new encyclopedia entries.
- **Edit Entry:** Existing entries can be edited by users.
- **Random Entry:** Users can view a random encyclopedia entry.

## Installation
To run the Encyclopedia App locally, follow these steps:

Clone the repository:
   ```bash
   git clone https://github.com/chris-gr81/application_portfolio/cs50w_project1_wiki/
   ```
Navigate to the project directory:
   ```bash
   cd application_portfolio/cs50w_project1_wiki
   ```
Install the required dependencies:
   ```bash
   pip install -r requirements.txt
   ```
Apply database migrations
   ```bash
   python manage.py migrate
   ```
Start the development server:
   ```bash
   python manage.py runserver
   ```
Access the application in your browser at 'http://localhost:8000'

## Usage
* View Entries: Visit the homepage to view a list of encyclopedia entries.
* Search: Use the search bar to find specific entries by title.
* View Entry Content: Click on an entry title to view its content.
* Create Entry: Click on "Create New Page" to create a new encyclopedia entry.
* Edit Entry: Click on "Edit" next to an entry to edit its content.
* Random Entry: Click on "Random Page" to view a random encyclopedia entry.
