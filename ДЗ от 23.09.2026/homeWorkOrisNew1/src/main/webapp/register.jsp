<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <h1>Регистрация</h1>

    <form method="post" action="${pageContext.request.contextPath}/register">
        <p>
            <label>Логин:</label><br>
            <input type="text" name="login" required>
        </p>

        <p>
            <label>Пароль:</label><br>
            <input type="password" name="password" required>
        </p>

        <button type="submit">Зарегистрироваться</button>
    </form>

    <p>
        Уже есть аккаунт?
        <a href="${pageContext.request.contextPath}/login">Войти</a>
    </p>
</div>

</body>
</html>
