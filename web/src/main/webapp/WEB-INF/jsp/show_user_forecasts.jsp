<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Forecasts</title>
</head>
<body>
    <p>Список прогнозов пользователя на выбранный турнир</p>
    <form action="${pageContext.request.contextPath}/listOfForecasts" method="post">
        <p>Выберите турнир для просмотра:</p>
        <select class="form-field" id="tournamentId" name="tournamentId">
            <c:forEach var="tournament" items="${requestScope.tournaments}">
                <option value="${tournament.id}"
                    <c:if test="${tournament.id == sessionScope.tournamentId}"> selected </c:if>
                >${tournament.name}</option>
            </c:forEach>
        </select>

        <p>Выберите пользователя:</p>
        <select class="form-field" id="userId" name="userId">
            <c:forEach var="user" items="${requestScope.users}">
                <option value="${user.id}"
                    <c:if test="${user.id == sessionScope.userId}"> selected </c:if>
                >${user.firstName} ${user.secondName}</option>
            </c:forEach>
        </select>

        <p> Только для оконченных матчей
            <input type="checkbox" name="matchState" value="1" <c:if test="${sessionScope.matchState == 1}">checked</c:if> >
        </p>

        <p>Количество записей на странице<p>
            <select class="form-field" id="recordsCnt" name="recordsCnt">
                <option value="5" <c:if test="${sessionScope.recordsCnt == 5}"> selected </c:if> >5</option>
                <option value="10" <c:if test="${sessionScope.recordsCnt == 10}"> selected </c:if> >10</option>
                <option value="20" <c:if test="${sessionScope.recordsCnt == 20}"> selected </c:if> >20</option>
            </select>
        <p><button class="btn-class" type="submit">Применить фильтр</button></p>
    </form>

    <c:if test="${sessionScope.pageCount != 0}">
        <c:forEach var="page" begin="0" end="${sessionScope.pageCount}" >
            <a href="${pageContext.request.contextPath}/listOfForecasts?pageId=${page}">${page + 1}</a>
        </c:forEach>
    </c:if>

    <c:if test="${not empty sessionScope.forecasts}">
        <table class="simple-little-table">
            <tr>
                <th>Команда 1</th>
                <th>Команда 2</th>
                <th>Прогноз 1</th>
                <th>Прогноз 2</th>
            </tr>
            <c:forEach var="forecast" items="${sessionScope.forecasts}">
                <tr class="widget-list3">
                    <td>${forecast.match.firstTeam.teamName}</td>
                    <td>${forecast.match.secondTeam.teamName}</td>
                    <td>${forecast.matchForecast.firstResult}</td>
                    <td>${forecast.matchForecast.secondResult}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</body>
</html>
