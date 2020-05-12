<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <%@ include file="template/head.jsp"%>
</head>
<body>
<div class="container-fluid mainPage">

    <main>
        <div class="heroSection">
            <%@ include file="template/navbar.jsp"%>
            <div class="display-2 text-center heading">
                Cooking Made Easy
            </div>

            <div class="text-center scrollReminder">
                Scroll Down
            </div>
        </div>

        <div class="searchingSection container">
            <h2 class="text-center">Search for New Recipes</h2>
            <form method="GET" action="searchRecipe" class="form col-8 offset-2" id="searchForm">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Enter search here" name="searchTerm" id="searchTerm">
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