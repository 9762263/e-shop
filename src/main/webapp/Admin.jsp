<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Calendar" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style>

</style>
</head>
<body>
<%-- Using JSTL forEach and out to loop a list and display items in table --%>
<table width=200px>
<tbody>
<tr><th width=100px>Товар</th><th width=50px>Цена</th></tr>
<c:forEach items="${requestScope.catalog}" var="catalog">

<tr align=center><td width=100px><c:out value="${catalog.type}"></c:out></td>
<td width=50px><c:out value="${catalog.price}"></c:out></td>
<td width=50px><a href="Admin?action=update">Изменить</a></td>
</tr>
</c:forEach>
</tbody>
</table>
<% if (Calendar.getInstance ().get (Calendar.AM_PM) == Calendar.AM) {%>
   Good Morning 
<% } else { %>
   Good Afternoon 
<% } %>
<br>
<% 
    String queryData = request.getCharacterEncoding() ; 
    out.println ("кодировка: " + queryData); 
%>
</body>
</html>