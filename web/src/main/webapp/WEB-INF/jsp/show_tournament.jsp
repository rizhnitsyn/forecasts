
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Tournament</title>
</head>
<body>

  <form action="${pageContext.request.contextPath}/tournament" method="post">
      <h2 class="form-title">Турнир: ${requestScope.tournament.name}</h2>
      <p>Дата начала игр турнира: <span class="span-class"> ${requestScope.tournament.startDate}</span></p>
      <p>Страна организатор турнира: <span class="span-class"> ${requestScope.tournament.organizer.teamName} </span></p>
      <p>Состояние турнира: <span class="span-class"> ${requestScope.tournament.stateId}</span></p>
      <c:if test="${not empty requestScope.tournament.matches}">
          <table class="simple-little-table">
              <tr>
                  <th>Матч</th>
                  <th>Счет</th>
                  <th>Дата матча</th>
              </tr>
              <c:forEach var="match" items="${requestScope.tournament.matches}">
                  <tr class="widget-list3">
                      <td>${match.firstTeam.teamName} - ${match.secondTeam.teamName}</td>
                      <td>
                          <c:if test="${not empty match.matchFinalResult}">
                              <span>${match.matchFinalResult.firstResult} - ${match.matchFinalResult.secondResult}</span>
                          </c:if>
                          <c:if test="${empty match.matchFinalResult}">
                              <span class="span-class_green">ожидается</span>
                          </c:if>
                      </td>
                      <td>${match.matchDateTime}</td>
                  </tr>
              </c:forEach>
          </table>
      </c:if>


  </form>
</body>
</html>
