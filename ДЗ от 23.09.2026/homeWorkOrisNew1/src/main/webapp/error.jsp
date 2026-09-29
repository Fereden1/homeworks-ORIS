<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>оошибка</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Ошибка</h1>
<p>${param.msg}</p>
<%
    String back = request.getParameter("back");
    String ctx = request.getContextPath();
    if ("register".equals(back)) {
%>
<p><a href="<%= ctx %>/register">назад к регистрации</a></p>
<%
    } else {
%>
<p><a href="<%= ctx %>/login">назад ко входу</a></p>
<% } %>

</body>
</html>
