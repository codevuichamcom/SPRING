<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>Form nguoi dung:  </p>
<c:url value="/sua-khach-hang" var="url"/>
<form:form modelAttribute="user" method="post" action="${url }">
	<form:hidden path="id"/>
	<p>Ten</p><form:input path="name"/>
	<p style="color:red"><form:errors path="name"></form:errors></p>
	<p>So dien thoai</p><form:input path="phone"/>
	<p style="color:red"><form:errors path="phone"></form:errors></p>
	<br/><br/>
	<form:button type="submit">Submit</form:button>
</form:form>
</body>
</html>