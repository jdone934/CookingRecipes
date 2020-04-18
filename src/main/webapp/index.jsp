<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style/master.css" />

    <title>Cooking Recipes</title>
</head>
<body>
<div class="container-fluid mainPage">

    <main>
        <div class="heroSection">
            <%@ include file="navbar.jsp"%>
            <div class="display-2 text-center heading">
                Cooking Made Easy
            </div>

            <div class="fixed-bottom text-center scrollReminder">
                Scroll Down
            </div>
        </div>

        <div class="searchingSection">
            <form method="GET" action="searchRecipe" class="form-inline">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Enter search here" id="searchTerm">
                </div>
                <div class="form-group">
                    <label for="searchType">Search Type:</label>
                    <select class="form-control" name="searchType" id="searchType">
                        <option value="name">Name</option>
                        <option value="category">Category</option>
                        <option value="description">Description</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </main>

    <footer>

    </footer>
</div>
</body>
</html>