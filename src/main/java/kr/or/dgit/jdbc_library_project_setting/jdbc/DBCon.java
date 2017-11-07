package kr.or.dgit.jdbc_library_project_setting.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBCon {
	private static final DBCon instance = new DBCon();
	private Connection connection;

	public static DBCon getInstance() {
		return instance;
	}

	private DBCon() {
		/*Properties properties = getProperties("conf.properties");*/
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost/?useSSL=false", "root", "rootroot");
		} catch (SQLException e) {
			System.err.printf("%s-%s%n", e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		}
	}

	private Properties getProperties(String propertiesPath) {
		Properties properties = new Properties();
		InputStream is=ClassLoader.getSystemResourceAsStream(propertiesPath);
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public Connection getConnection() {
		return connection;
	}

	
}
