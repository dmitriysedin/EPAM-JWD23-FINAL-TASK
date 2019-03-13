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
    <fmt:message bundle="${loc}" key="locale.default.locale_button.en" var="locale_button_en" />
    <fmt:message bundle="${loc}" key="locale.default.locale_button.ru" var="locale_button_ru" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.welcome_message" var="welcome_message" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.movie_title" var="movie_title" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.movie_director" var="movie_director" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.movie_released_year" var="movie_released_year" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.add_movie" var="add_movie" />
    <fmt:message bundle="${loc}" key="locale.default.go_to_home_page" var="home" />
    <fmt:message bundle="${loc}" key="locale.addNewMovie.movie_released_year_pattern" var="movie_released_year_pattern" />
   	<fmt:message bundle="${loc}" key="locale.default.go_to_home_page" var="home" />
    <fmt:message bundle="${loc}" key="locale.default.enter_login_message" var="login" />
    <fmt:message bundle="${loc}" key="locale.default.registration_message" var="registration" />
    <fmt:message bundle="${loc}" key="locale.default.logged_message" var="logged_message" />
    <fmt:message bundle="${loc}" key="locale.default.logout_message" var="logout_message" />
  
	<table align="center" width="80%" cellspacing="0" cellpadding="4">
		<tr>
		<td align="center" width="70">
		<form action="Servlet" method="post" >
		<input type="hidden" name="command" value="goToShowAllMoviesPageCommand">
		<input type="submit" size="50" value="${home}" />
		</form>
		</td>
		<td align="center" width="70">
		<form action="Servlet" method="post" >
		<input type="hidden" name="command" value="changeLocale">
   		<input type="hidden" name="locale" value="ru">
		<input type="submit" size="50" value="${locale_button_ru}" />
		</form>
		</td>
		<td align="center" width="70">
		<form action="Servlet" method="post" >
		<input type="hidden" name="command" value="changeLocale">
   		<input type="hidden" name="locale" value="en">
		<input type="submit" value="${locale_button_en}" />
		</form>
		</td>
		<td align="center" width="70">
		<form action="Servlet" method="post" >
		<input type="hidden" name="command" value="goToRegistrationPage">
		<input type="submit" value="${registration}" />
		</form>
		</td>
		<td align="right" width="70">
		<form action="Servlet" method="post" >
		<input type="hidden" name="command" value="goToLoginPage">
		<input type="submit" value="${login}" />
		</form>
		</td>
		<td align="right" width="300">
		<c:if test="${!empty sessionScope.user}">
		<c:out value="${logged_message } - "  />
		</c:if>
		</td>
		<td align="left" width="200">
		<c:if test="${!empty sessionScope.user}">
		<input type="button" value="${sessionScope.user.userFirstName}" />
		</c:if>
		</td>
		<td align="right" width="70">
		<form action="Servlet" method="post" >
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

<h1>${welcome_message}</h1>

	<form action="Servlet" method="post">
		<table width="100%" cellspacing="0" cellpadding="4">
		<tr>
		<td align="right" width="200">${movie_title}</td>
		<td><input type="text" size="50" name="movie_title" required /></td>
		</tr>
		<tr>
		<td align="right">${movie_director}</td>
		<td><input type="text" size="50" name="movie_director" required /></td>
		</tr>
		<tr>
		<td align="right" title="${movie_released_year_pattern }">${movie_released_year}</td>
		<td><input type="text" size="50" name="movie_released_year" pattern="[0-9]{4,4}" required /></td>
		</tr>
		<tr>
		<tr>
		<td></td>
		<td>
		<input type="hidden" name="command" value="addNewMovie">
		<input type="submit" value="${add_movie}" />
		</td>
		</tr>
		</table>
	</form>

	<h3>
		<c:out value="${requestScope.error}" />
		</h3>
	
</body>
</html>