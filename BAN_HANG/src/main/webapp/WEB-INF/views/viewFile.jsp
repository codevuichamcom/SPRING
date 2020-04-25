<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/style.css'/>">
<script src="<c:url value='/static/js/main.js'/>"></script>
</head>
<body>
<p>Thong tin nguoi dung: </p>
<p>File name: ${file.getOriginalFilename() }</p>

</body>
</html>