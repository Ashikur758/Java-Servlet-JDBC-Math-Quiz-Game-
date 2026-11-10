<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.GameScore, util.Html" %>
<!DOCTYPE html><html><head><title>Leaderboard - MATH WHIZ</title>
<link rel="stylesheet" href="css/style.css"></head><body>
<div class="box" style="max-width:760px">
  <h1>Top 10 Leaderboard</h1>
  <% if (request.getAttribute("dbError") != null) { %><div class="err"><%= Html.esc((String) request.getAttribute("dbError")) %></div><% } %>
  <table>
    <tr><th>#</th><th>Player</th><th>Score</th><th>Difficulty</th><th>Date/Time</th></tr>
    <%
      @SuppressWarnings("unchecked")
      List<GameScore> list = (List<GameScore>) request.getAttribute("scores");
      if (list != null) { int rank = 1; for (GameScore g : list) { %>
    <tr><td><%= rank++ %></td><td><%= Html.esc(g.getPlayerName()) %></td><td><%= g.getScore() %></td>
        <td><%= Html.esc(g.getDifficulty()) %></td><td><%= g.getPlayedAt() %></td></tr>
    <% } } %>
  </table>
  <a class="btn" href="index.jsp">Home</a>
</div></body></html>
