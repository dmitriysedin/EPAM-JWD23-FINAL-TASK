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

<h1>Add your rate</h1>

	<form action="Servlet" method="post">
		<input type="hidden" name="command" value="addNewRate">
		RATE <input type="text" name="rate_value" required value="" /> <br /> 
		COMMENT <input type="text" name="rate_comment" required value="" /> <br />  
		
		<input type="submit" name="addNewRate" value="Add rate" />
	</form>

	<h3>
		<c:out value="${requestScope.error}" />
		</h3>
	
</body>
</html>