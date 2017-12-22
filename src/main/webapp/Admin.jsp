<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>

</style>
</head>
<body>
<%-- Using JSTL forEach and out to loop a list and display items in table --%>
<table width=500px>
<tbody>
<tr><th width=250px>Товар</th><th width=250px>Цена</th></tr>
<c:forEach items="${requestScope.catalog}" var="catalog">
<tr align=center><td width=250px><c:out value="${catalog.type}"></c:out></td>
<td width=250px><c:out value="${catalog.price}"></c:out></td></tr>
</c:forEach>
</tbody>
</table>
</body>
</html>