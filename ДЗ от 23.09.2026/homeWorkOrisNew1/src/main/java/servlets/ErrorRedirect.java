package servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ErrorRedirect {

    public static void send(HttpServletRequest req, HttpServletResponse resp, String msg, String back) throws IOException {
        String encoded = URLEncoder.encode(msg, StandardCharsets.UTF_8);
        resp.sendRedirect(req.getContextPath() + "/error?msg=" + encoded + "&back=" + back);
    }
}
