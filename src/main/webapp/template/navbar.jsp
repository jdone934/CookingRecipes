<nav class="navbar navbar-expand-sm navbar-dark">
    <a href="/CookingRecipes" id="homeLink">Easy Cook</a>
    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#collapsibleNavbar" aria-expanded="false">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-collapse justify-content-end collapse" id="collapsibleNavbar" style="">
        <ul class="navbar-nav">
            <c:set var="path" value = "${path}" />

            <c:if test="${empty path}">
                <c:set var="path" value = "/" />
            </c:if>

            <c:if test="${not empty pageContext.request.getRemoteUser()}">
                <li class="nav-item">
                    <a class="nav-link" href="profile">Profile</a>
                </li>
            </c:if>

            <c:if test="${empty pageContext.request.getRemoteUser()}">
                <li class="nav-item">
                    <a class="nav-link" href="signUp${path}">Sign Up</a>
                </li>
            </c:if>

            <li class="nav-item">

                <c:if test="${empty pageContext.request.getRemoteUser()}">
                    <a class="nav-link" href="login${path}" >Login</a>
                </c:if>

                <c:if test="${not empty pageContext.request.getRemoteUser()}">
                    <a class="nav-link" href="logout${path}" >Logout</a>
                </c:if>
            </li>
        </ul>
    </div>
</nav>
