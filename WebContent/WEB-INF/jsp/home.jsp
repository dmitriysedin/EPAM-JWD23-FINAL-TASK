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
    <fmt:message bundle="${loc}" key="locale.default.welcom_message" var="welcome_message" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.en" var="locale_button_en" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.ru" var="locale_button_ru" />
    <fmt:message bundle="${loc}" key="locale.default.enter_login_message" var="login" />
    <fmt:message bundle="${loc}" key="locale.default.registration_message" var="registration" />
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

<a href="Servlet?command=goToDefaultPage">${login}</a>
<a href="Servlet?command=goToRegistrationPage">${registration}</a>
<a href="Servlet?command=goToAddNewMovie">${add_movie}</a>
<a href="Servlet?command=showAllMovies">Movie's list</a>
	
	<h3>
		<c:out value="${requestScope.error}" />
		</h3>
	
</body>
</html>