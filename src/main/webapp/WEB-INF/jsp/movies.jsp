<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <link href="${pageContext.servletContext.contextPath}/static/css/movies.css" rel="stylesheet" type="text/css">
    <title>Movies</title>
</head>
<body>
<c:if test="${empty sessionScope.username}">
    <c:redirect url="/login-form"/>
</c:if>
<jsp:include page="header.jsp"/>
<div class="wrapper">
    <main class="content">
        <div class="content-top">
            <c:if test="${sessionScope.role eq 'ADMIN'}">
                <button class="add" onclick="openAddMovieModal()">
                    <span>+</span>
                    <fmt:message key="add_Movie"/>
                </button>
            </c:if>
        </div>
        <div class="Movies">
            <c:set value='${sessionScope["moviesList"]}' var="moviesList"/>
            <c:forEach var="movie" items="${moviesList}">
                <br>
                <div class="movie">
                    <a href="<c:url value="/review-page?movieId=${movie.getId()}"/>">
<%--                        <img src="${movie.getImgUrl()}" alt="${movie.getTitle()}" class="poster">--%>
                        <div class="rating">${movie.getRating()}</div>
                        <div class="name">${movie.getTitle()}</div>
                    </a>
                    <c:if test="${sessionScope.role eq 'ADMIN'}">
                        <button class="add edit"
                                onclick="openEditMovieModal(`${movie.getId()}`, `${movie.getTitle()}`, `${movie.getDescription()}`)">
                            <fmt:message key="edit_movie"/>
                        </button>
                    </c:if>
                </div>
            </c:forEach>
        </div>

        <div class="modal-wrapper" id="addMovieModal">
            <div class="modal-form">
                <h2 class="modal-header"><fmt:message key="add_Movie_header"/></h2>
                <form action="<c:url value="/add-movie"/>" method="post">
                    <input type="hidden" name="command" value="add_Movie">

                    <label for="addMovieTitle"><fmt:message key="movie_title"/></label>
                    <input type="text" name="movieTitle" id="addMovieTitle" required>

                    <label for="addMovieDescription"><fmt:message key="movie_description"/></label>
                    <textarea name="movieDescription" id="addMovieDescription" required></textarea>

                    <label for="addMovieYear"><fmt:message key="movie_title"/></label>
                    <input type="number" name="movieYear" id="addMovieYear" required>

<%--                    <label for="addImgUrl"><fmt:message key="Movie_image_url"/></label>--%>
<%--                    <input type="text" name="imgUrl" id="addImgUrl" required>--%>

                    <button class="modal-submit" type="submit">
                        <fmt:message key="Movie_add_btn"/>
                    </button>
                </form>
            </div>
        </div>

        <div class="modal-wrapper" id="editMovieModal">
            <div class="modal-form">
                <h2 class="modal-header"><fmt:message key="edit_Movie_header"/></h2>
                <form action="<c:url value="/update-movie"/>" method="post">
                    <input type="hidden" name="command" value="edit_Movie">

                    <input type="hidden" name="movieId" id="editMovieId" value="">
                    <label for="editMovieTitle"><fmt:message key="Movie_title"/></label>
                    <input type="text" name="movieTitle" id="editMovieTitle" required>

                    <label for="editMovieDescription"><fmt:message key="Movie_description"/></label>
                    <textarea name="movieDescription" id="editMovieDescription" required></textarea>

                    <label for="addMovieYear"><fmt:message key="movie_year"/></label>
                    <input type="number" name="movieYear" id="addMovieYear" required>

<%--                    <label for="editMovieImgUrl"><fmt:message key="Movie_image_url"/></label>--%>
<%--                    <input type="text" name="movieImgUrl" id="editMovieImgUrl" required>--%>

                    <button class="modal-submit" type="submit">
                        <fmt:message key="Movie_edit_btn"/>
                    </button>
                </form>
            </div>
        </div>
    </main>
</div>
<script>
    openAddMovieModal = () => {
        const modal = document.getElementById("addMovieModal");
        modal.style.display = "flex";
    }

    openEditMovieModal = (MovieId, MovieTitle, MovieDescription) => {
        const modal = document.getElementById("editMovieModal");
        modal.style.display = "flex";
        document.getElementById("editMovieId").value = movieId;
        document.getElementById("editMovieTitle").value = movieTitle;
        document.getElementById("editMovieDescription").value = movieDescription;
        // document.getElementById("editMovieImgUrl").value = imgUrl;
    }

    window.onclick = (event) => {
        const addModal = document.getElementById("addMovieModal");
        const editModal = document.getElementById("editMovieModal");
        if (event.target === addModal) {
            addModal.style.display = "none";
        }
        if (event.target === editModal) {
            editModal.style.display = "none";
        }
    }
</script>
</body>
</html>
