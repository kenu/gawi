package net.okjsp.gawi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
		} finally {
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

	public ArrayList<Game> load() {
		ArrayList<Game> list = new ArrayList<Game>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from game order by id";
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			while(rs.next()) {
				Game game = new Game();
				game.setChoice(rs.getInt("choice"));
				game.setComputerChoice(rs.getInt("computerChoice"));
				game.setJudgement(rs.getString("judgement"));
				game.setDatetime(rs.getTimestamp("datetime"));
				
				list.add(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list ;
	}
}
