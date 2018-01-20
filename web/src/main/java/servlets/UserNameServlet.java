package servlets;

import by.forecasts.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userName")
public class UserNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("username", new UserService().getUserName());
        req.getRequestDispatcher("/WEB-INF/jsp/show-user-name.jsp")
                .forward(req, resp);
    }
}
