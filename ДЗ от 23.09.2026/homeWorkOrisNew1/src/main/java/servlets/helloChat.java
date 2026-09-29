package servlets;

import dao.impl.messageDaoImpl;
import dao.messageCRUD;
import entities.Message;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/chat")
public class helloChat extends HttpServlet {

    private final messageCRUD messageDao = messageDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.setAttribute("messages", messageDao.findAll());
        req.getRequestDispatcher("/hellochat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
        String messageText = req.getParameter("message");

        if (messageText != null && !messageText.isBlank()) {
            Message message = new Message(
                    user.getId(),
                    messageText
            );
            messageDao.save(message);
        }
        resp.sendRedirect(req.getContextPath() + "/chat");
    }
}