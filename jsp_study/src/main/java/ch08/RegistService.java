package ch08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistService {
	Map<String, Regist> regists = new HashMap<>();
	
	public RegistService() {
		//DB에서 가져왔다고 치는 가상의 데이터를 생성한다.
		Regist r1 = new Regist("101", "김지우", "서울시", "silver", "010-1111-1111");
		Regist r2 = new Regist("102", "홍길동", "인천시", "gold", "010-2222-2222");
		Regist r3 = new Regist("103", "율곡", "김포시", "vip", "010-3333-3333");
		
		regists.put("101", r1);
		regists.put("102", r2);
		regists.put("103", r3);		
	}
	
	//모든 상품 리스트 가져오기
	public ArrayList<Regist> findAll () {
		//hashmap에 있는 데이터들을 가져와서 넣어줌
		return new ArrayList<>(regists.values());
	}
	
	//특정 상품을 가져온다.
	public Regist find(String id) {
		return regists.get(id);
	}
	
}
