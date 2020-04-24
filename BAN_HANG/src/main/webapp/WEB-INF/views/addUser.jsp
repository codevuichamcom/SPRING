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
<c:url value="/them-user" var="url"/>
<form:form modelAttribute="user" method="post" action="${url }" enctype="multipart/form-data">
	<p>Ten</p><form:input path="name"/>
	<p style="color:red"><form:errors path="name"></form:errors></p>
	<p>Mat khau</p><form:password path="password"/>
	<p style="color:red"><form:errors path="password"></form:errors></p>
	<form:hidden path="id"/>
	<p>So thich</p>
	
	<!-- doi voi check box c1 -->
	<%-- <form:checkbox path="favourites" value="Xem phim" label="Xem Phim" />
	<form:checkbox path="favourites" value="Nghe Nhac" label="Nghe Nhac" />
	<form:checkbox path="favourites" value="Code" label="Coding" /> --%>
	<!-- doi voi check box c2 -->
	<form:checkboxes items="${listFavourites }" path="favourites"/>
	<p>Gioi tinh</p>
	<form:select path="gender">
		<form:option value="Nam">Nam</form:option>
		<form:option value="Nu">Nu</form:option>
	</form:select>
	<br/>
	<p>Gioi thieu</p>
	<form:textarea path="about"/>
	<br/>
	<form:radiobutton path="acceptAgreement" value="true" label="Dong y dieu khoan"/>
	<br/>
	<form:input path="avatar" type="file"/>
	<br/><br/>
	<form:button type="submit">Submit</form:button>
</form:form>
</body>
</html>