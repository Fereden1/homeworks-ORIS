<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>подтверждение</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <h1>подтверждение аккаунта</h1>

    <p>
        аккаунт создан со статусом pending. Откройте бота и отправьте ему код
    </p>

    <p>
        <a href="https://t.me/${botUsername}?start=${confirmationCode}" target="_blank">
            Открыть TГ-бота
        </a>
    </p>

    <p>ваш код</p>

    <h2>${confirmationCode}</h2>

    <p>
        после сообщения бота аккаунт подтверждён. Можно войти логином и паролем
    </p>

    <p>
        <a href="${pageContext.request.contextPath}/login">Войти</a>
    </p>
</div>

</body>
</html>
```
