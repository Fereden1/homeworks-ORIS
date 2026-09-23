package servlets;

import entities.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/chat")
public class helloChat extends HttpServlet {

    private static final List<Message> messages = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/hellochat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String nickname = req.getParameter("nickname");
        String message = req.getParameter("message");

        if(nickname != null && !nickname.isBlank() && message != null && !message.isBlank()){
            Message text = new Message();
            messages.add(text);
        }
        resp.sendRedirect(req.getContextPath() + "/chat");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
