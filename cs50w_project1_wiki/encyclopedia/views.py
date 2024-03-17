from django.shortcuts import render
from django.http import HttpResponse, HttpResponseRedirect
from . import util
import markdown2
import random


def index(request):
    """Render the startpage of encyclopedia

    Arguments:
        request (HttpRequest): The request made to the server

    Returns:
        HttpResponse: Rendered HTML template with a list of encyclopeida entries
    """
    return render(request, "encyclopedia/index.html", {
        "entries": util.list_entries()
    })


def content(request, name):
    """Render the content of a specific encyclopedia page

    Arguments:
        request (HttpRequest): The request made to the server
        name (str): The title of the encyclopedia page to display

    Returns:
         HttpResponse: Rendered HTML template with the content of the specified encyclopedia page
    """
    if name in util.list_entries():
        return render(request, "encyclopedia/content.html", {
            "article": markdown2.markdown(util.get_entry(name)),
            "title": name
        })
    else:
        return render(request, "encyclopedia/errorpage.html", {
            "message": name + " does not exist as page"
        })


def search(request):
    """Search for encyclopedia entries matching a given query

    Arguments:
        request (HttpRequest): The request made to the server

    Returns:
        HttpResponse: Rendered HTML template with search results matching the query
    """
    query = (request.POST.get('q', ''))
    if query in util.list_entries():
        return render(request, "encyclopedia/content.html", {
            "article": markdown2.markdown(util.get_entry(query)),
            "title": query
        })
    else:
        matches = []
        for match in util.list_entries():
            if query in match:
                matches.append(match)
        return render(request, "encyclopedia/search_missmatch.html", {
            "matches": matches,
            "query": query
        })


def create_page(request):
    """Allow users to create a new encyclopedia page

    Arguments:
        request (HttpRequest): The request made to the server

    Returns:
       HttpResponse: Rendered HTML template for creating a new encyclopedia page
    """
    if request.method == "POST":
        new_title = (request.POST.get('title'))
        new_entry = (request.POST.get('entry'))
        overwrite = (request.POST.get('edit'))

        if new_title in util.list_entries() and overwrite == "no":
            return render(request, "encyclopedia/errorpage.html", {
                "message": "There is allready an article called " + new_title
            })
        else:
            util.save_entry(new_title, new_entry)

        return render(request, "encyclopedia/content.html", {
            "article": markdown2.markdown(util.get_entry(new_title)),
            "title": new_title
        })

    else:
        return render(request, "encyclopedia/create_page.html")


def edit_page(request):
    """Allow users to edit an existing encyclopedia page

    Arguments:
        request (HttpRequest): The request made to the server

    Returns:
        HttpResponse: Rendered HTML template for editing an encyclopedia page
    """
    if request.method == "POST":
        edit_title = (request.POST.get('title'))
        edit_article = util.get_entry(edit_title)

    return render(request, "encyclopedia/edit_page.html", {
        "title": edit_title,
        "content": edit_article
    })


def random_page(request):
    """Display a random encyclopedia page

    Arguments:
        request (HttpRequest): The request made to the server

    Returns:
        HttpResponse: Rendered HTML template with the content of a randomly selected encyclopedia page
    """
    random_number = random.randint(0, len(util.list_entries()) - 1)
    random_title = util.list_entries()[random_number]

    return render(request, "encyclopedia/content.html", {
        "article": markdown2.markdown(util.get_entry(random_title)),
        "title": random_title
    })
