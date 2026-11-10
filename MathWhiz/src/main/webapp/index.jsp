<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="util.Html" %>
<!DOCTYPE html><html><head><title>Ashikur  - MATH WHIZ</title>
<link rel="stylesheet" href="css/style.css"></head><body>
<div class="box">
  <h1>Ashikur  – MATH WHIZ</h1>
  <p style="text-align:center">Prime • Modulus • Trigonometry &nbsp;|&nbsp; Difficulty: NORMAL</p>
  <% if (request.getAttribute("error") != null) { %><div class="err"><%= Html.esc((String) request.getAttribute("error")) %></div><% } %>
  <form method="post" action="game">
    <input type="hidden" name="action" value="start">
    <label>Player Name</label>
    <input type="text" name="playerName" maxlength="100" required>
    <button type="submit">Start Game</button>
    <a class="btn" href="game?action=leaderboard">Leaderboard</a>
  </form>
</div></body></html>
