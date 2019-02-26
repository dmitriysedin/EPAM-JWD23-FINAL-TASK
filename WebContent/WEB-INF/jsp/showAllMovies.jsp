<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
 <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="resources.locale" var="loc" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.en" var="locale_button_en" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.ru" var="locale_button_ru" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.welcome_message" var="welcome_message" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.movie_title" var="movie_title" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.movie_director" var="movie_director" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.movie_released_year" var="movie_released_year" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.add_movie" var="add_movie" />
   
<div align="right">
<form action="Servlet" method="post" >
   <input type="hidden" name="command" value="changeLocale">
   <input type="hidden" name="locale" value="ru">
   <input type="submit" name="${locale_button_ru}" value="${locale_button_ru}" />
</form>
<form action="Servlet" method="post" >
   <input type="hidden" name="command" value="changeLocale">
   <input type="hidden" name="locale" value="eng">
   <input type="submit" name="${locale_button_en}" value="${locale_button_en}" />
</form>
</div>

<h1>${welcome_message}</h1>

<table cellspacing="2" border="1" cellpadding="5" bordercolor="gray">
		<caption>
			<h1>List of movies</h1>
		</caption>
		<br>
		<tr>
			<th>Movie's id</th>
			<th>Movie's title</th>
			<th>Movie's director</th>
			<th>Movie's released year</th>
		</tr>
		<c:set var="count" scope="request" value="${0}"></c:set>
		<c:forEach items="${moviesList}" var="movies">
					<c:if test="${count >= firstrow && count < lastrow}">
						<tr>
							<td><c:out value="${movies.movieID}"></c:out></td>
							<td><c:out value="${movies.movieTitle}"></c:out></td>
							<td><c:out value="${movies.movieDirector}"></c:out></td>
							<td><c:out value="${movies.movieReleasedYear}"></c:out></td>
						</tr>
					</c:if>
					<c:set var="count" value="${count + 1}"></c:set>
				</c:forEach>
	</table>
	<form action="Servlet" method="post">
		<c:if test="${count > lastrow}">
			<input type="submit" name="command" value="showNextPage">
		</c:if>
		<c:if test="${firstrow > 0}">
			<input type="submit" name="command" value="showPreviousPage">
		</c:if>
	</form>

	<h3>
		<c:out value="${requestScope.error}" />
		</h3>
	
</body>
</html>