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
	<fmt:message bundle="${loc}" key="locale.error.error" var="error" />
	<fmt:message bundle="${loc}" key="locale.error.info500" var="info500" />

	<fmt:message bundle="${loc}" key="locale.default.go_to_home_page"
		var="home" />

	<table align="center" width="70%" cellspacing="0" cellpadding="4">
		<tr>
			<td align="center" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command"
						value="goToShowAllMoviesPageCommand"> <input type="submit"
						size="50" value="${home}" />
				</form>
			</td>
		</tr>
	</table>
	<br>
	<br>

	<H1>${error} 500</H1>
	<h2>${info500}</h2>

</body>
</html>
