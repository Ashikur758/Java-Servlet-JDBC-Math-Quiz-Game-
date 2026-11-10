# YOUR NAME – MATH WHIZ

A web-based math game built with Java Servlet/JSP and JDBC (MySQL). Four questions (Prime, Modulus, 2x Trigonometry) are evaluated with real Java methods; scores are stored in a database and the Top 10 are shown on a leaderboard.

## Objectives
Reusable Java math methods, servlet request/response handling, JDBC with PreparedStatement, leaderboard querying, GitHub documentation.

## Technologies
Java 17, Jakarta Servlet 6 / JSP, JDBC, MySQL 8, Maven, Apache Tomcat 10.1+

## Software Requirements
JDK 17+, Maven 3.8+, Tomcat 10.1+, MySQL 8+, any IDE (IntelliJ / Eclipse / NetBeans)

## Database Configuration
Edit `URL`, `USER`, `PASS` in `src/main/java/dao/ScoreDAO.java`.

## Database SQL
```sql
CREATE DATABASE IF NOT EXISTS math_whiz_db;
USE math_whiz_db;
CREATE TABLE IF NOT EXISTS game_scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    difficulty VARCHAR(20) NOT NULL,
    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Run Steps
1. Run `database/math_whiz_db.sql` in MySQL.
2. Update DB credentials in `ScoreDAO.java`.
3. `mvn clean package`
4. Copy `target/MathWhiz-1.0.war` to Tomcat `webapps/` and start Tomcat.
5. Open http://localhost:8080/MathWhiz-1.0/

## Game Rules & Scoring
| Q | Area | Marks |
|---|------|-------|
| 1 | Prime (1/0) | 2 |
| 2 | Modulus | 2 |
| 3 | Trig | 3 |
| 4 | Trig | 3 |

Total 10. Difficulty: NORMAL. Trig answers accepted within ±0.01. Angles are in degrees (converted to radians).

## Application Screenshots
### Welcome Page
![Welcome Page](screenshots/home.png)
### Game Page
![Game Page](screenshots/game.png)
### Result Page
![Result Page](screenshots/result.png)
### Leaderboard
![Leaderboard](screenshots/leaderboard.png)

## GitHub Directory Link
https://github.com/username/repository/tree/main/MathWhiz
