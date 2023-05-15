package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import javax.swing.JOptionPane;

public class dbConnecter {

	Connection conn; // java.sql.Connection
	Statement stmt = null;
	String server_addr = "";
	String table_name = "";
	String username = "";
	String password = "";
	
	
	public void getDBInfo() {
		//서버 주소, 테이블 이름, DB 사용자 이름, 비밀번호가 저장되는 파일입니다.
		//함께 포함된 파일 (example)DBInfo.txt를 적절히 수정한 후 파일 제목을 DBInfo.txt로 저장하세요.
		File file = new File("DBInfo.txt");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		try {
			server_addr = bufferedReader.readLine();
			table_name = bufferedReader.readLine();
			username = bufferedReader.readLine();
			password = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println(server_addr);
			System.out.println(table_name);
			System.out.println(username);
			System.out.println(password);
		}
		
	}
	
	public dbConnecter() {
		
		// 생성자가 실행되면 DB에 자동 연결되어 Connection 객체 생성
		// server : harinoyama.com:3306
		// ID : swa4
		// PW : DEUA4program!

		getDBInfo();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://"+server_addr+":3306/"+table_name, username,password);
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			JOptionPane.showMessageDialog(null, "DB 연결 오류", "DB 연결 오류", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public ResultSet executeQuery(String sql) {
		//SQL문 실행하기 위한 메소드 - Parameter : String객체로 만든 SQL문
		//실행결과는 ResultSet으로 반환
		System.out.println(sql);
		ResultSet src = null;
		try {
			src = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(src);
			System.out.println("SQL 실행 에러");
			JOptionPane.showMessageDialog(null, "SQL 실행 에러", "SQL 실행 에러", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		return src;
	}
	
	public Connection getConnection() {
		//PreparedStatement이용해 SQL 작성할 경우 Connection 객체가 필요해 만든 메소드
		
		if(conn!=null) {
			return conn;
		}else {
			return null;
		}
		
	}
	
}