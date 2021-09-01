package day20_DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBClass {
	// private Connection con;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "han";
	private String pwd = "rlfdyd0603";
	public DBClass() {
		//1. 자바에서 오라클에 관련된 기능을 사용할 수 있게 기능을 등록하는 것
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// con = DriverManager.getConnection(null);
		} catch (Exception e) {
			e.printStackTrace(); //빨간 글씨를 띄우게 해 어떤 에러인지 알려주는 역할
		}
	}
	public void getList() {
		// 2. 데이터베이스 연결(con 은 DB에 연결된 객체다)
		try {
			Connection con = DriverManager.getConnection(url, id, pwd); //1.데이터베이스의 장소 2.id 3.pw
			System.out.println("연결이 잘 이루어 졌습니다!!");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// 3. 데이터베이스에 연결된 객체를 이용해서 명령어를 수행할 수 있는 객체를 얻어온다
		// 4. 명령어를 수행할 수 있는 객체를 이용해서 명령어 수행
		// 5. 수행 결과를 저장한다.
	}
}
