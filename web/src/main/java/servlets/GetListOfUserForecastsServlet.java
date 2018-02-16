package servlets;

import by.forecasts.service.ForecastServiceImpl;
import by.forecasts.service.TournamentServiceImpl;
import by.forecasts.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listOfForecasts")
public class GetListOfUserForecastsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tournaments", TournamentServiceImpl.getInstance().getListOfTournaments());
        req.setAttribute("users", UserServiceImpl.getInstance().getListOfUsers());

        if (req.getParameter("pageId") != null) {
            Long tournamentId = (Long) req.getSession().getAttribute("tournamentId");
            Long userId = (Long) (req.getSession().getAttribute("userId"));
            int recordsCnt = (int) (req.getSession().getAttribute("recordsCnt"));
            Long matchStateId =  (Long) req.getSession().getAttribute("matchState");
            Integer pageId = Integer.parseInt(req.getParameter("pageId"));

            List<Object[]> userForecasts = ForecastServiceImpl.getInstance().getUserForecasts(tournamentId, userId,
                matchStateId, recordsCnt, pageId);
            req.getSession().removeAttribute("forecasts");
            req.getSession().setAttribute("forecasts", userForecasts);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/show_user_forecasts.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tournamentId = Long.parseLong(req.getParameter("tournamentId"));
        Long userId = Long.parseLong(req.getParameter("userId"));
        int recordsCnt = Integer.valueOf(req.getParameter("recordsCnt"));
        Long matchStateId = 1L;
        if (req.getParameter("matchState") == null) {
            matchStateId = 2L;
        }
        Long countOfUserForecasts = ForecastServiceImpl.getInstance().getCountOfUserForecasts(tournamentId, userId, matchStateId);

        Long pagesCount =  countOfUserForecasts / recordsCnt;
        if (countOfUserForecasts % recordsCnt != 0) {
            pagesCount += 1;
        }

        List<Integer> pageList = new ArrayList<>();
        for (int i = 0; i < pagesCount; i++) {
            pageList.add(i + 1);
        }
        List<Object[]> userForecasts = ForecastServiceImpl.getInstance().getUserForecasts(tournamentId, userId,
                matchStateId, recordsCnt, 1);

        req.getSession().setAttribute("pageList", pageList);
        req.getSession().setAttribute("forecasts", userForecasts);
        req.getSession().setAttribute("tournamentId", tournamentId);
        req.getSession().setAttribute("userId", userId);
        req.getSession().setAttribute("matchState", matchStateId);
        req.getSession().setAttribute("recordsCnt", recordsCnt);

        resp.sendRedirect("/listOfForecasts");
    }
}
