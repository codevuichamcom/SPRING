<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Form nguoi dung:  </p>
<c:url value="/them-khach-hang" var="url"/>
<form:form modelAttribute="user" method="post" action="${url }">
	<p>Ten</p><form:input path="name"/>
	<p style="color:red"><form:errors path="name"></form:errors></p>
	<p>So dien thoai</p><form:input path="phone"/>
	<p style="color:red"><form:errors path="phone"></form:errors></p>
	<br/><br/>
	<form:button type="submit">Submit</form:button>
</form:form>
</body>
</html>