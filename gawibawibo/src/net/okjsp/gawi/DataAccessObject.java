package net.okjsp.gawi;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataAccessObject {
	private static SqlSessionFactory sqlSessionFactory;

	static {
		String resource = "net/okjsp/gawi/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	public List<Game> load() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Game> list = null;
		try {
			GameMapper mapper = session.getMapper(GameMapper.class);
			list = mapper.getGameList();
		} finally {
			session.close();
		}
		
		return list ;
	}
}
