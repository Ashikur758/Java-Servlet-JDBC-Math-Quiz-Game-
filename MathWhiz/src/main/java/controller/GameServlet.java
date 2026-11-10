package controller;

import dao.ScoreDAO;
import model.GameScore;
import service.MathService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Random;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private static final String DIFFICULTY = "NORMAL";
    private static final int[] SIN_COS_ANGLES = {0, 30, 45, 60, 90, 120, 150, 180};
    private static final int[] TAN_ANGLES     = {0, 30, 45, 60, 120, 135, 150};
    private final Random rnd = new Random();
    private final ScoreDAO dao = new ScoreDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("leaderboard".equals(req.getParameter("action"))) {
            showLeaderboard(req, resp);
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("start".equals(action)) startGame(req, resp);
        else if ("submit".equals(action)) submitGame(req, resp);
        else resp.sendRedirect("index.jsp");
    }

    private void startGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("playerName");
        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("error", "Please enter your name.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
        HttpSession s = req.getSession();
        s.setAttribute("playerName", name.trim());
        s.setAttribute("primeNum", rnd.nextInt(99) + 2);          // 2..100
        s.setAttribute("modA", rnd.nextInt(90) + 10);             // 10..99
        s.setAttribute("modB", rnd.nextInt(9) + 2);               // 2..10 (never 0)
        String[] fns = {"sin", "cos", "tan"};
        String f1 = fns[rnd.nextInt(3)], f2 = fns[rnd.nextInt(3)];
        s.setAttribute("t1fn", f1); s.setAttribute("t1ang", pickAngle(f1));
        s.setAttribute("t2fn", f2); s.setAttribute("t2ang", pickAngle(f2));
        req.getRequestDispatcher("game.jsp").forward(req, resp);
    }

    private int pickAngle(String fn) {
        int[] arr = fn.equals("tan") ? TAN_ANGLES : SIN_COS_ANGLES;
        return arr[rnd.nextInt(arr.length)];
    }

    private void submitGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("primeNum") == null) { resp.sendRedirect("index.jsp"); return; }

        int primeNum = (Integer) s.getAttribute("primeNum");
        int a = (Integer) s.getAttribute("modA");
        int b = (Integer) s.getAttribute("modB");
        String f1 = (String) s.getAttribute("t1fn"); int g1 = (Integer) s.getAttribute("t1ang");
        String f2 = (String) s.getAttribute("t2fn"); int g2 = (Integer) s.getAttribute("t2ang");

        boolean primeCorrect = false, modCorrect = false, trig1Correct = false, trig2Correct = false;
        String err = null;

        try {
            int ansPrime = Integer.parseInt(req.getParameter("ans1").trim());
            primeCorrect = (ansPrime == MathService.isPrime(primeNum));
            int ansMod = Integer.parseInt(req.getParameter("ans2").trim());
            modCorrect = (ansMod == MathService.calculateMod(a, b));
            double t1 = Double.parseDouble(req.getParameter("ans3").trim());
            trig1Correct = MathService.closeEnough(MathService.calculateTrig(f1, g1), t1);
            double t2 = Double.parseDouble(req.getParameter("ans4").trim());
            trig2Correct = MathService.closeEnough(MathService.calculateTrig(f2, g2), t2);
        } catch (NumberFormatException e) {
            err = "Invalid input: Q1 and Q2 need integers, Q3 and Q4 need numbers.";
        } catch (IllegalArgumentException | ArithmeticException e) {
            err = e.getMessage();
        }

        if (err != null) {
            req.setAttribute("error", err);
            req.getRequestDispatcher("game.jsp").forward(req, resp);
            return;
        }

        int totalScore = 0;
        if (primeCorrect) totalScore += 2;
        if (modCorrect)   totalScore += 2;
        if (trig1Correct) totalScore += 3;
        if (trig2Correct) totalScore += 3;

        String name = (String) s.getAttribute("playerName");
        try {
            dao.save(new GameScore(name, totalScore, DIFFICULTY));
        } catch (Exception e) {
            req.setAttribute("dbError", "Could not save score: " + e.getMessage());
        }
        req.setAttribute("score", totalScore);
        req.setAttribute("r1", primeCorrect); req.setAttribute("r2", modCorrect);
        req.setAttribute("r3", trig1Correct); req.setAttribute("r4", trig2Correct);
        s.removeAttribute("primeNum");
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }

    private void showLeaderboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("scores", dao.getTop10());
        } catch (Exception e) {
            req.setAttribute("dbError", "Database error: " + e.getMessage());
        }
        req.getRequestDispatcher("leaderboard.jsp").forward(req, resp);
    }
}
