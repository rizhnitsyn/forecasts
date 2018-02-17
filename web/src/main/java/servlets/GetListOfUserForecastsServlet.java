package servlets;

import by.forecasts.config.ApplicationContextHolder;
import by.forecasts.service.ForecastService;
import by.forecasts.service.TournamentService;
import by.forecasts.service.UserService;

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
        TournamentService tournamentService = ApplicationContextHolder.getBean(TournamentService.class);
        UserService userService = ApplicationContextHolder.getBean(UserService.class);
        ForecastService forecastService = ApplicationContextHolder.getBean(ForecastService.class);

        req.setAttribute("tournaments", tournamentService.getListOfTournaments());
        req.setAttribute("users", userService.getListOfUsers());

        if (req.getParameter("pageId") != null) {
            Long tournamentId = (Long) req.getSession().getAttribute("tournamentId");
            Long userId = (Long) (req.getSession().getAttribute("userId"));
            int recordsCnt = (int) (req.getSession().getAttribute("recordsCnt"));
            Long matchStateId =  (Long) req.getSession().getAttribute("matchState");
            Integer pageId = Integer.parseInt(req.getParameter("pageId"));

            List<Object[]> userForecasts = forecastService.getUserForecasts(tournamentId, userId,
                matchStateId, recordsCnt, pageId);
            req.getSession().removeAttribute("forecasts");
            req.getSession().setAttribute("forecasts", userForecasts);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/show_user_forecasts.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ForecastService forecastService = ApplicationContextHolder.getBean(ForecastService.class);

        Long tournamentId = Long.parseLong(req.getParameter("tournamentId"));
        Long userId = Long.parseLong(req.getParameter("userId"));
        int recordsCnt = Integer.valueOf(req.getParameter("recordsCnt"));
        Long matchStateId = 1L;
        if (req.getParameter("matchState") == null) {
            matchStateId = 2L;
        }
        Long countOfUserForecasts = forecastService.getCountOfUserForecasts(tournamentId, userId, matchStateId);

        Long pagesCount =  countOfUserForecasts / recordsCnt;
        if (countOfUserForecasts % recordsCnt != 0) {
            pagesCount += 1;
        }

        List<Integer> pageList = new ArrayList<>();
        for (int i = 0; i < pagesCount; i++) {
            pageList.add(i + 1);
        }
        List<Object[]> userForecasts = forecastService.getUserForecasts(tournamentId, userId,
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
