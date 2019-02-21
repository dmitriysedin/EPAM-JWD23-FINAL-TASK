<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<h1> W E L C O M E, </h1>
<h2>
 	<c:out value="${requestScope.user.userFirstName}" />
 </h2>

</body>
</html>