package day20_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	public ArrayList<StudentDTO> getList() {
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		// 2. 데이터베이스 연결(con 은 DB에 연결된 객체다)
		try {
			Connection con = DriverManager.getConnection(url, id, pwd); //1.데이터베이스의 장소 2.id 3.pw
			System.out.println("연결이 잘 이루어 졌습니다!!");
			// 3. 데이터베이스에 연결된 객체를 이용해서 명령어를 수행할 수 있는 객체를 얻어온다
			String sql = "select * from newst";
			PreparedStatement ps = con.prepareStatement(sql); //ps = 전송객체
			// 4. 명령어를 수행할 수 있는 객체를 이용해서 명령어 수행
			ResultSet rs = ps.executeQuery(); //명령어를 수행해라 rs = 데이터베이스에서 가지고 온 모든 데이터가 존재
			// 5. 수행 결과를 저장한다.
			/*while(rs.next()) {
				System.out.println("id : "+ rs.getString("id"));
				System.out.println("name : "+ rs.getString("name"));
				System.out.println("age : "+ rs.getInt("age"));
				System.out.println("--------------------------------");
			}*/
			while(rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public StudentDTO searchST(String userId) {
		//select * from newst where id = '222';
		String sql = "select * from newst where id = '"+userId+"'";
		StudentDTO dto = null;
		try {
			//1. 디비 연결
			Connection con = DriverManager.getConnection(url, id, pwd);
			System.out.println("연결이 잘 이루어졌습니다.");
			//2. 명령어(쿼리문) 전송 객체 생성
			PreparedStatement ps = con.prepareStatement(sql);
			//3. 전송 후 결과값 저장
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				dto = new StudentDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	public int saveData(String userId,String userName,int userAge) {
		// String sql = "insert into newst values("+userId+"','"+userName+"'.'"+userAge+"')";
		String sql = "insert into newst values(? , ? , ?)";
		int result = 0;
		try {
			// 1. 데이터베이스 연결 객체 얻어오기
			Connection con = DriverManager.getConnection(url,id,pwd);
			// 2. 쿼리문 전송객체 얻어오기
			PreparedStatement ps = con.prepareStatement(sql);
			// 3. 자리 채우기
			ps.setString(1, userId);
			ps.setString(2, userName);
			ps.setInt(3, userAge); //물음표에 대한 순서
			// 4. 쿼리문 전송(실행)
			// ResultSet rs = ps.executeQuery(); //select쿼리문일 때 executeQuery
			// select 를 제외한 다른 쿼리문은 executeUpdate() 사용
			// executeUpdate 는 int 형태의 값을 돌려준다. 성공1, 실패 0 또는 에러
			result = ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updateData(String userId, String userName, int age) {
		String sql = "update newst set name = ? , age = ? where id = ?";
		int result = 0;
		try {
			Connection con = DriverManager.getConnection(url, id, pwd);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(3, userId);
			ps.setString(1, userName);
			ps.setInt(2, age);
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int deleteData(String userId) {
		int result = 0;
		String sql = "delete from newst where id='"+userId+"'";
		// String sql = "delete from newst where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(url,id,pwd);
			ps = con.prepareStatement(sql);
			//ps.setString(1, userId); // sql ? 쿼리문일 때 사용
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
