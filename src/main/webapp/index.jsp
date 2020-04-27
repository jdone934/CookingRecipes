<!doctype html>
<html lang="en">
<head>
    <%@ include file="template/template-head.jsp"%>
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

        <div class="searchingSection">
            <form method="GET" action="searchRecipe" class="form-inline">
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

        <a href="search.jsp">Testing authentication</a>
    </main>

    <footer>

    </footer>
</div>
</body>
</html>