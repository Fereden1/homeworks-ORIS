package servlets;

import dao.impl.userDaoImpl;
import dao.userCRUD;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tg.TelegramBot;

import java.io.IOException;
import java.security.SecureRandom;

@WebServlet("/register")
public class registerServlet extends HttpServlet {

    private final userCRUD userDao = userDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            ErrorRedirect.send(req, resp, "заполнии все поля", "register");
            return;
        }

        login = login.trim();

        if (userDao.existsByLogin(login)) {
            ErrorRedirect.send(req, resp, "такой логин уже занят", "register");
            return;
        }

        String code = generateCode();

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setConfirmationStatus("pending");
        user.setConfirmationCode(code);
        userDao.save(user);

        req.setAttribute("confirmationCode", code);
        req.setAttribute("botUsername", TelegramBot.BOT_USERNAME);
        req.getRequestDispatcher("/confirmation.jsp").forward(req, resp);
    }

    private String generateCode() {
        SecureRandom random = new SecureRandom();
        String code;
        do {
            code = String.valueOf(100000 + random.nextInt(900000));
        } while (userDao.findByConfirmationCode(code) != null);
        return code;
    }
}
