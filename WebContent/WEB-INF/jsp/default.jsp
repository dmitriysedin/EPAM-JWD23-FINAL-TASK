<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="resources.locale" var="loc" />
    <fmt:message bundle="${loc}" key="locale.default.welcom_message" var="welcom_message" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.en" var="locale_button_en" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.ru" var="locale_button_ru" />
    <fmt:message bundle="${loc}" key="locale.default.enter_login_message" var="enter_login_message" />
    <fmt:message bundle="${loc}" key="locale.default.enter_password_message" var="enter_password_message" />
    <fmt:message bundle="${loc}" key="locale.default.enter_message" var="enter_message" />
    <fmt:message bundle="${loc}" key="locale.default.registration_message" var="registration_message" />

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


	<h1>${welcom_message}</h1>
	
	<h3>
		<c:out value="${requestScope.registration_result}" />
	</h3>

	<form action="Servlet" method="post">
		<input type="hidden" name="command" value="authorization">
		${enter_login_message} <input type="text" name="login" required value="" /> <br /> 
		${enter_password_message} <input type="password" name="password" required value="" /> <br /> 
		<input type="submit" name="enter" value="${enter_message}" />
	</form>

	<h3>
		<c:out value="${requestScope.error}" />
	</h3>

	<a href="Servlet?command=goToRegistrationPage">${registration_message}</a>


	
</body>
</html>