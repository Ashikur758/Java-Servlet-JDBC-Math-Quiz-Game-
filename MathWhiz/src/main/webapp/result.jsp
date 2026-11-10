<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="util.Html" %>
<% boolean r1=(Boolean)request.getAttribute("r1"), r2=(Boolean)request.getAttribute("r2"),
          r3=(Boolean)request.getAttribute("r3"), r4=(Boolean)request.getAttribute("r4"); %>
<!DOCTYPE html><html><head><title>Result - MATH WHIZ</title>
<link rel="stylesheet" href="css/style.css"></head><body>
<div class="box">
  <h1>Result</h1>
  <p>Player: <b><%= Html.esc((String) session.getAttribute("playerName")) %></b></p>
  <div class="score"><%= request.getAttribute("score") %> / 10</div>
  <% if (request.getAttribute("dbError") != null) { %><div class="err"><%= Html.esc((String) request.getAttribute("dbError")) %></div><% } %>
  <table>
    <tr><th>Question</th><th>Result</th></tr>
    <tr><td>Q1 Prime (2)</td><td class="<%= r1 ? "ok" : "bad" %>"><%= r1 ? "Correct" : "Wrong" %></td></tr>
    <tr><td>Q2 Modulus (2)</td><td class="<%= r2 ? "ok" : "bad" %>"><%= r2 ? "Correct" : "Wrong" %></td></tr>
    <tr><td>Q3 Trig (3)</td><td class="<%= r3 ? "ok" : "bad" %>"><%= r3 ? "Correct" : "Wrong" %></td></tr>
    <tr><td>Q4 Trig (3)</td><td class="<%= r4 ? "ok" : "bad" %>"><%= r4 ? "Correct" : "Wrong" %></td></tr>
  </table>
  <a class="btn" href="index.jsp">Play Again</a>
  <a class="btn" href="game?action=leaderboard">Leaderboard</a>
</div></body></html>
