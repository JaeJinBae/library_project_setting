package kr.or.dgit.jdbc_library_project_setting.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import kr.or.dgit.jdbc_library_project_setting.dao.DatabaseDao;

public class InitServiceTrigger implements DbService {
	private static final InitServiceTrigger instance=new InitServiceTrigger();
	
	public static InitServiceTrigger getInstance() {
		return instance;
	}

	private InitServiceTrigger() {

	}
	
	@Override
	public void service() {
		File f = new File(System.getProperty("user.dir") + "\\resources\\create_trigger.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(f));) {
			StringBuilder statement = new StringBuilder();
			for (String line; (line = br.readLine()) != null;) {
				if (!line.isEmpty() && !line.startsWith("--")) {
					statement.append(line.trim()+" ");
					System.out.println(line);
				}
				if (line.endsWith("$$")) {
					statement.replace(statement.lastIndexOf("$$"), statement.length(), "");
					DatabaseDao.getInstance().executeUpdateSQL(statement.toString());
					statement.setLength(0);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
