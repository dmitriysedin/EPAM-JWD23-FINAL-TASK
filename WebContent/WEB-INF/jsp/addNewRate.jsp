<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="resources.locale" var="loc" />
	<fmt:message bundle="${loc}" key="locale.default.locale_button.en"
		var="locale_button_en" />
	<fmt:message bundle="${loc}" key="locale.default.locale_button.ru"
		var="locale_button_ru" />
	<fmt:message bundle="${loc}" key="locale.addNewRate.welcome_message"
		var="welcome_message" />
	<fmt:message bundle="${loc}" key="locale.addNewRate.add_rate"
		var="add_rate" />
	<fmt:message bundle="${loc}" key="locale.addNewRate.enter_add_rate"
		var="enter_add_rate" />
	<fmt:message bundle="${loc}" key="locale.addNewRate.add_comment"
		var="add_comment" />
	<fmt:message bundle="${loc}" key="locale.default.go_to_home_page"
		var="home" />
	<fmt:message bundle="${loc}" key="locale.addNewRate.rate_pattern"
		var="rate_pattern" />
	<fmt:message bundle="${loc}" key="locale.default.go_to_home_page"
		var="home" />
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

	<h1>${welcome_message}</h1>

	<form action="Servlet" method="post">
		<input type="hidden" name="command" value="addNewRate">
		<table width="100%" cellspacing="0" cellpadding="4">
			<tr>
				<td align="right" width="200" title="${rate_pattern}">${add_rate}
				</td>
				<td><input type="number" size="50" name="rate_value" min="1"
					max="10" required /></td>
			</tr>
			<tr>
				<td align="right" valign="top">${add_comment}</td>
				<td><textarea name="rate_comment" cols="38" rows="10" required></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="${enter_add_rate}" /></td>
			</tr>
		</table>
	</form>

</body>
</html>