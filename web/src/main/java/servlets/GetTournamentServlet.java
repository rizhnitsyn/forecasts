package servlets;

import by.forecasts.services.TournamentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tournament")
public class GetTournamentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tournament", TournamentService.getInstance().getTournamentById(1L));
        req.getRequestDispatcher("/WEB-INF/jsp/show_tournament.jsp")
                .forward(req, resp);
    }
}
