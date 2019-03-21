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
	<fmt:message bundle="${loc}" 
		key="locale.registration.welcome_message" var="welcome_message" />
	<fmt:message bundle="${loc}" 
		key="locale.default.locale_button.en" var="locale_button_en" />
	<fmt:message bundle="${loc}" 
		key="locale.default.locale_button.ru" var="locale_button_ru" />
	<fmt:message bundle="${loc}"
		key="locale.registration.enter_first_name_message" var="enter_first_name" />
	<fmt:message bundle="${loc}"
		key="locale.registration.enter_last_name_message" var="enter_last_name" />
	<fmt:message bundle="${loc}"
		key="locale.registration.enter_email_message" var="enter_email" />
	<fmt:message bundle="${loc}"
		key="locale.registration.enter_login_message" var="enter_login" />
	<fmt:message bundle="${loc}"
		key="locale.registration.enter_password_message" var="enter_password" />
	<fmt:message bundle="${loc}"
		key="locale.registration.first_name_pattern" var="first_name_pattern" />
	<fmt:message bundle="${loc}"
		key="locale.registration.last_name_pattern" var="last_name_pattern" />
	<fmt:message bundle="${loc}" 
		key="locale.registration.email_pattern" var="email_pattern" />
	<fmt:message bundle="${loc}" 
		key="locale.registration.wrong_params"	var="registration_wrong_params" />
	<fmt:message bundle="${loc}" 
		key="locale.login.login_pattern" var="login_pattern" />
	<fmt:message bundle="${loc}" 
		key="locale.login.pass_pattern"	var="pass_pattern" />
	<fmt:message bundle="${loc}"
		key="locale.registration.enter_registration_message" var="enter_registration" />
	<fmt:message bundle="${loc}" 
		key="locale.default.go_to_home_page" var="home" />
	<fmt:message bundle="${loc}" 
		key="locale.default.go_to_home_page" var="home" />
	<fmt:message bundle="${loc}" 
		key="locale.default.enter_login_message" var="login" />
	<fmt:message bundle="${loc}" 
		key="locale.default.registration_message" var="registration" />
	<fmt:message bundle="${loc}" 
		key="locale.default.logged_message" var="logged_message" />
	<fmt:message bundle="${loc}" 
		key="locale.default.logout_message" var="logout_message" />

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

	<h1>${welcome_message}</h1>

	<form action="Servlet" method="post">

		<table width="100%" cellspacing="0" cellpadding="4">
			<tr>
				<td align="right" width="200" title="${first_name_pattern}">${enter_first_name}</td>
				<td><input type="text" size="50" name="first_name"
					pattern="^([A-Za-zА-Яа-яЁё]){3,44}$" required /></td>
			</tr>
			<tr>
				<td align="right" title="${last_name_pattern}">${enter_last_name}</td>
				<td><input type="text" size="50" name="last_name"
					pattern="^([A-Za-zА-Яа-яЁё]){3,44}$" required /></td>
			</tr>
			<tr>
				<td align="right" title="${email_pattern}">${enter_email}</td>
				<td><input type="text" size="50" name="email"
					pattern="^(\w+[\.-]?\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6})$" required /></td>
			</tr>
			<tr>
				<td align="right" width="100" title="${login_pattern}">${enter_login}</td>
				<td><input type="text" size="50" name="login"
					pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$" required /></td>
			</tr>
			<tr>
				<td align="right" title="${pass_pattern}">${enter_password}</td>
				<td><input type="password" size="51" name="password"
					pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$" required /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="hidden" name="command" value="registration">
					<input type="submit" value="${enter_registration}" /></td>
			</tr>
		</table>

	</form>

	<h3>
		<c:if test="${!empty param.wrong_params}">
		<c:out value="${registration_wrong_params}" />
		</c:if>
	</h3>

</body>
</html>