<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/static/css/style.css'/>">
<script src="<c:url value='/static/js/main.js'/>"></script>
</head>
<body>
	<p>Hello: ${msg }</p>
	<p>Path Variable: ${info }</p>
	<img src="<c:url value='/static/image/cute.jpg'/>" />
</body>
</html>