package net.okjsp.gawi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DataAccessObject {

	public int save(Game game) {
		// getConnection()
		Connection conn = getConnection();
		String sql = "insert into game (choice, computerChoice, judgement, datetime) values (?, ?, ?, ?)";
		// Statement
		int result = 0;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, game.getChoice());
			statement.setInt(2, game.getComputerChoice());
			statement.setString(3, game.getJudgement());
			statement.setTimestamp(4, new Timestamp(game.getDatetime()
					.getTime()));

			// result
			result = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// close statement
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// close connection()
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/javatest", "javauser",
					"javadude");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
