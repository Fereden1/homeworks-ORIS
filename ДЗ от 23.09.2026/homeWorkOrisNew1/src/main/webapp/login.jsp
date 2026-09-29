<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <h1>Вход</h1>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <p>
            <label>Логин</label><br>
            <input type="text" name="login" required>
        </p>

        <p>
            <label>Пароль</label><br>
            <input type="password" name="password" required>
        </p>

        <button type="submit">Войти</button>
    </form>

    <p>
        Нет аккаунта?
        <a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a>
    </p>
</div>

</body>
</html>
```
