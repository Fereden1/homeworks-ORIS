package servlets;

import dao.impl.userDaoImpl;
import dao.userCRUD;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class loginServlet extends HttpServlet {

    private final userCRUD userDao = userDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            ErrorRedirect.send(req, resp, "заполни логин и пароль", "login");
            return;
        }

        User user = userDao.findByLogin(login.trim());

        if (user == null) {
            ErrorRedirect.send(req, resp, "такого логина нет", "login");
            return;
        }
        if (!user.getPassword().equals(password)) {
            ErrorRedirect.send(req, resp, "логин верный, но пароль не", "login");
            return;
        }
        if (!"confirmed".equals(user.getConfirmationStatus())) {
            ErrorRedirect.send(req, resp, "аккаунт ещё не подтверждён. Отправьте код боту в ТГ, затем войдите снова", "login");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/chat");
    }
}
