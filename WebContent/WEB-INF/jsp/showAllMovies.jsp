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
	<fmt:message bundle="${loc}" key="locale.default.locale_button.en"
		var="locale_button_en" />
	<fmt:message bundle="${loc}" key="locale.default.locale_button.ru"
		var="locale_button_ru" />
	<fmt:message bundle="${loc}" key="locale.addNewMovie.movie_id"
		var="movie_id" />
	<fmt:message bundle="${loc}" key="locale.addNewMovie.movie_title"
		var="movie_title" />
	<fmt:message bundle="${loc}" key="locale.addNewMovie.movie_director"
		var="movie_director" />
	<fmt:message bundle="${loc}"
		key="locale.addNewMovie.movie_released_year" var="movie_released_year" />
	<fmt:message bundle="${loc}" key="locale.addNewMovie.add_movie"
		var="add_movie" />
	<fmt:message bundle="${loc}" key="locale.addNewMovie.movie_rate"
		var="movie_rate" />
	<fmt:message bundle="${loc}" key="locale.addNewMovie.movie_list_title"
		var="movie_list_title" />
	<fmt:message bundle="${loc}" key="locale.default.go_to_home_page"
		var="home" />
	<fmt:message bundle="${loc}" key="locale.default.page_from" var="from" />
	<fmt:message bundle="${loc}" key="locale.default.page" var="page" />
	<fmt:message bundle="${loc}" key="locale.default.next_page"
		var="next_page" />
	<fmt:message bundle="${loc}" key="locale.default.previoues_page"
		var="previous_page" />
	<fmt:message bundle="${loc}" key="locale.default.enter_login_message"
		var="login" />
	<fmt:message bundle="${loc}" key="locale.default.registration_message"
		var="registration" />
	<fmt:message bundle="${loc}" key="locale.default.logged_message"
		var="logged_message" />
	<fmt:message bundle="${loc}" key="locale.default.logout_message"
		var="logout_message" />

	<table align="center" width="80%" cellspacing="0" cellpadding="4">
		<tr>
			<td align="center" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command"
						value="goToShowAllMoviesPageCommand"> <input type="submit"
						size="50" value="${home}" />
				</form>
			</td>
			<td align="center" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command" value="changeLocale"> <input
						type="hidden" name="locale" value="ru"> <input
						type="submit" size="50" value="${locale_button_ru}" />
				</form>
			</td>
			<td align="center" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command" value="changeLocale"> <input
						type="hidden" name="locale" value="en"> <input
						type="submit" value="${locale_button_en}" />
				</form>
			</td>
			<td align="center" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command" value="goToRegistrationPage">
					<input type="submit" value="${registration}" />
				</form>
			</td>
			<td align="right" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command" value="goToLoginPage">
					<input type="submit" value="${login}" />
				</form>
			</td>
			<td align="right" width="300"><c:if
					test="${!empty sessionScope.user}">
					<c:out value="${logged_message } - " />
				</c:if></td>
			<td align="left" width="200"><c:if
					test="${!empty sessionScope.user}">
					<input type="button" value="${sessionScope.user.userFirstName}" />
				</c:if></td>
			<td align="right" width="70">
				<form action="Servlet" method="post">
					<c:if test="${!empty sessionScope.user}">
						<input type="hidden" name="command" value="logOutCommand">
						<input type="submit" value="${logout_message}" />
					</c:if>
				</form>
			</td>
		</tr>
	</table>
	<br>
	<br>


	<table cellspacing="2" border="1" cellpadding="5" bordercolor="gray">
		<caption><font size="6">${movie_list_title}</font></caption>

		<tr>
			<th>${movie_id}</th>
			<th>${movie_title}</th>
			<th>${movie_director}</th>
			<th>${movie_released_year}</th>
			<th>${movie_rate}</th>
		</tr>
		<c:set var="count" scope="request" value="${0}"></c:set>
		<c:forEach items="${sessionScope.moviesList}" var="movies">
			<c:if
				test="${count >= sessionScope.allMoviesFirstRow && count < sessionScope.allMoviesLastRow}">
				<tr>
					<td align="center" width="75"><c:out value="${movies.movieID}"></c:out></td>
					<td align="center" width="200"><a
						href="Servlet?command=concreteMovieInfo&movie_id=${movies.movieID}">${movies.movieTitle}</a></td>
					<td align="center" width="100"><c:out
							value="${movies.movieDirector}"></c:out></td>
					<td align="center" width="100"><c:out
							value="${movies.movieReleasedYear}"></c:out></td>
					<td align="center" width="75"><c:out
							value="${movies.movieAVGRate}"></c:out></td>
				</tr>
			</c:if>
			<c:set var="count" value="${count + 1}"></c:set>
		</c:forEach>
	</table>
	<br>
	<form action="Servlet" method="post">
		<c:if test="${count > sessionScope.allMoviesLastRow}">
			<input type="hidden" name="first_row_parameter_name"
				value="allMoviesFirstRow">
			<input type="hidden" name="last_row_parameter_name"
				value="allMoviesLastRow">
			<input type="hidden" name="number_of_pages_parameter_name"
				value="allMoviesNumberOfPages">
			<input type="hidden" name="current_page_number_parameter_name"
				value="allMoviesCurrentPageNumber">
			<input type="hidden" name="current_page_URL"
				value="Servlet?command=goToShowAllMoviesPageCommand">
			<input type="hidden" name="command" value="showNextPage">
			<input type="submit" value="${next_page }">
		</c:if>
	</form>

	<form action="Servlet" method="post">
		<c:if test="${sessionScope.allMoviesFirstRow > 0}">
			<input type="hidden" name="first_row_parameter_name"
				value="allMoviesFirstRow">
			<input type="hidden" name="last_row_parameter_name"
				value="allMoviesLastRow">
			<input type="hidden" name="number_of_pages_parameter_name"
				value="allMoviesNumberOfPages">
			<input type="hidden" name="current_page_number_parameter_name"
				value="allMoviesCurrentPageNumber">
			<input type="hidden" name="current_page_URL"
				value="Servlet?command=goToShowAllMoviesPageCommand">
			<input type="hidden" name="command" value="showPreviousPage">
			<input type="submit" value="${previous_page }">
		</c:if>
	</form>

	<h4>
		<c:out
			value="${page} ${sessionScope.allMoviesCurrentPageNumber} ${from } ${sessionScope.allMoviesNumberOfPages}" />
	</h4>
	<br>
	<c:if
		test="${!empty sessionScope.user && sessionScope.user.userRole == 'ADMIN'}">
		<form action="Servlet" method="post">
			<input type="hidden" name="command" value="goToAddNewMovie">
			<input type="submit" value="${add_movie}" />
		</form>
	</c:if>
	<br>
	<h3>
		<c:out value="${requestScope.error}" />
	</h3>

</body>
</html>