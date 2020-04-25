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
<p>Ten: ${u.name }</p>
<p>Mat khau: ${u.password }</p>
<p>Id: ${u.id }</p>
<p>Gioi Tinh: ${u.gender }</p>
<p>Gioi thieu: ${u.about }</p>
<p>Agreement: ${u.acceptAgreement }</p>
<p>So thich</p>
<c:forEach items="${u.favourites }" var="item">
	<p>${item }</p>
</c:forEach>

<p>File name: ${u.avatar.getOriginalFilename() }</p>
</body>
</html>