<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Message" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>servlets.helloChat</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/hellochat.css">
</head>

<body>
<header>
</header>
<main>
    <section class="chat__section">
        <div class="chat__sidebar">
            <div class="chat__sidebar--header">
                <p class="text">Чат</p>
            </div>
            <div class="chat__messages">
                <%List<Message> messages = (List<Message>) request.getAttribute("messages");
                    if (messages != null) {for (Message message : messages) {%>
                <div class="message">
                    <div class="message__top">
                        <strong><%= message.getNickname() %></strong>
                        <span><%= message.getFormattedTime() %></span>
                    </div>
                    <p><%= message.getText() %></p>
                </div>
                <%
                    }
                    }
                %>
            </div>

            <div class="chat__sidebar--messages">
                <form class="chat__form" method="post" action="<%= request.getContextPath() %>/chat">
                    <input type="text" name="nickname" placeholder="nickname" required>
                    <input type="text" name="message" placeholder="message" required>
                    <button>Отправить</button>
                </form>
            </div>

        </div>
    </section>
</main>

<footer> <p>by Saifutdinov Farit 11-502<p> </footer>

<script src="<%= request.getContextPath() %>/js/hellochat.js"></script>
</body>

</html>