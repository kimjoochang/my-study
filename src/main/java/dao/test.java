package dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.ListDao;
import dto.List;

public class test {

	public static void main(String[] args) {
//		String contents = "solution";
//		String name = "kim";
//		int priority = 1;
//		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
//		
//		List list = new List(contents, name, priority, time);
//		
//		ListDao dao = new ListDao();
//		int insertCount = dao.addList(list);
//		
//		System.out.println(insertCount);
		ListDao dao = new ListDao();
		List list = dao.getList("game");
		System.out.println(list);
	}

}
