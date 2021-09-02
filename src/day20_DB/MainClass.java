package day20_DB;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num, age, result=0;
		String id,name;
		ArrayList<StudentDTO> list = null;
		DBClass db = new DBClass();
		
		while(true) {
			System.out.println("1. 모든데이터보기 2. 검색 \n3. 저장 4. 수정 5. 삭제");
			num = sc.nextInt();
			switch(num) {
			case 1 : 
				// 데이터베이스의 모든 데이터를 가져와서 보여준다
				list = db.getList();
				for(int i=0;i<list.size();i++) {
					System.out.println("id : "+ list.get(i).getId());
					System.out.println("name : "+ list.get(i).getName());
					System.out.println("age : "+ list.get(i).getAge());
					System.out.println("--------------------------------");
				}
				break;
			case 2 : 
				// 검색 데이터를 데이터베이스에서 가져오기
				System.out.println("검색 id 입력");
				id = sc.next();
				StudentDTO dto = db.searchST(id);
				if(dto != null) {
					System.out.println("id = "+ dto.getId());
					System.out.println("name = "+ dto.getName());
					System.out.println("age = "+ dto.getAge());
				}else {
					System.out.println("해당 아이디는 존재하지 않습니다.");
				}
				break;
			case 3 : 
				//저장
				System.out.println("아이디 입력");
				id = sc.next();
				System.out.println("이름 입력");
				name = sc.next();
				System.out.println("나이 입력");
				age = sc.nextInt();
				
				result = db.saveData(id, name, age);
				if(result ==1) {
					System.out.println("저장성공");
				}else {
					System.out.println("동일한 아이디가 존재합니다.");
				}
				break;
			case 4 : 
				// 수정
				System.out.println("수정할 아이디 입력(존재하는 아이디 입력)");
				id = sc.next();
				System.out.println("수정(변경)할 이름 입력");
				name = sc.next();
				System.out.println("수정(변경)할 나이 입력");
				age = sc.nextInt();
				result = db.updateData(id, name, age);
				if(result ==1 ) {
					System.out.println("저장 성공");
				}else {
					System.out.println("저장된 아이디가 존재하지 않습니다.");
				}
				break;
			case 5 : 
				// 삭제
				System.out.println("삭제할 아이디 입력");
				id = sc.next();
				result = db.deleteData(id);
				if(result ==1) {
					System.out.println("정상적으로 삭제 되었습니다.");
				}else {
					System.out.println("해당하는 아이디는 존재하지 않습니다.(삭제실패)");
				}
				break;
			}
		}
	}
}
