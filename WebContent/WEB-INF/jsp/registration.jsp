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
    <fmt:message bundle="${loc}" key="locale.registration.welcome_message" var="welcome_message" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.en" var="locale_button_en" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.ru" var="locale_button_ru" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_first_name_message" var="enter_first_name" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_last_name_message" var="enter_last_name" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_email_message" var="enter_email" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_login_message" var="enter_login" />
     <fmt:message bundle="${loc}" key="locale.registration.enter_password_message" var="enter_password" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_registration_message" var="enter_registration" />

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

	<form action="Servlet" method="post">
		<input type="hidden" name="command" value="registration">
		${enter_first_name} <input type="text" name="first_name" required value="" /> <br /> 
		${enter_last_name} <input type="text" name="last_name" required value="" /> <br /> 
		${enter_email} <input type="text" name="email" required value="" /> <br /> 
		${enter_login} <input type="text" name="login" required value="" /> <br /> 
		${enter_password} <input type="password" name="password" required value="" /> <br /> 
		<input type="submit" name="registration" value="${enter_registration}" />
	</form>

	<h3>
		<c:out value="${requestScope.error}" />
		</h3>
	
</body>
</html>