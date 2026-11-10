<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="util.Html" %>
<%
  if (session.getAttribute("primeNum") == null) { response.sendRedirect("index.jsp"); return; }
%>
<!DOCTYPE html><html><head><title>Game - MATH WHIZ</title>
<link rel="stylesheet" href="css/style.css"></head><body>
<div class="box">
  <h1>YOUR NAME – MATH WHIZ</h1>
  <p>Player: <b><%= Html.esc((String) session.getAttribute("playerName")) %></b></p>
  <% if (request.getAttribute("error") != null) { %><div class="err"><%= Html.esc((String) request.getAttribute("error")) %></div><% } %>
  <form method="post" action="game">
    <input type="hidden" name="action" value="submit">
    <label>Q1 (2 marks): Is <%= session.getAttribute("primeNum") %> prime? Enter 1 = Prime, 0 = Not Prime</label>
    <input type="text" name="ans1" required>
    <label>Q2 (2 marks): What is <%= session.getAttribute("modA") %> % <%= session.getAttribute("modB") %> ?</label>
    <input type="text" name="ans2" required>
    <label>Q3 (3 marks): <%= session.getAttribute("t1fn") %>(<%= session.getAttribute("t1ang") %>°) = ? (2 decimals)</label>
    <input type="text" name="ans3" required>
    <label>Q4 (3 marks): <%= session.getAttribute("t2fn") %>(<%= session.getAttribute("t2ang") %>°) = ? (2 decimals)</label>
    <input type="text" name="ans4" required>
    <button type="submit">Submit Answers</button>
  </form>
</div></body></html>
