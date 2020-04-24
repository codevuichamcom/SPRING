<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Danh sach nguoi dung</h2>
	<hr />
	<a href="<c:url value="/them-khach-hang"/>">Them Khach Hang</a>
	<table>
		<tr>
			<th>ID</th>
			<th>Ten</th>
			<th>So Dt</th>
			<th>Chon</th>
		</tr>

		<c:forEach items="${users }" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.name }</td>
				<td>${user.phone }</td>
				<td>
					<a href="<c:url value="/chi-tiet-khach-hang/${user.id }"/>">Chi tiet</a>
					<a href="<c:url value="/xoa-khach-hang/${user.id }"/>">Xoa</a>
					<a href="<c:url value="/sua-khach-hang/${user.id }"/>">Sua</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>

</body>
</html>