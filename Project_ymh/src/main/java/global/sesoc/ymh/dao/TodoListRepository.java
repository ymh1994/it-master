package global.sesoc.ymh.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.ymh.dto.Todo;

@Repository
public class TodoListRepository {

	@Autowired
	SqlSession session;
	
	public int insert(Todo todo) {
		
		TodoListMapper mapper = session.getMapper(TodoListMapper.class);
		System.out.println(todo+"dddd");
		return mapper.insert(todo);
		
	}

	public ArrayList<Todo> selectAll(String userId,String searchItem, String searchWord,int startRecord, int countPerPage) {
		
		
		RowBounds rb = new RowBounds(startRecord,countPerPage);
		TodoListMapper mapper = session.getMapper(TodoListMapper.class);
		Map<String,String>map = new HashMap<>();
		map.put("userId", userId);
		map.put("searchItem", searchItem);
		map.put("searchWord", searchWord);
		
		return mapper.selectAll(map,rb);
	}

	
 	public int delete(int todoSeq) {
		
		TodoListMapper mapper = session.getMapper(TodoListMapper.class);
		return mapper.delete(todoSeq);
	}


 	public int getCount(String userId,String searchItem,String searchWord){
 		TodoListMapper mapper = session.getMapper(TodoListMapper.class);
 		
 		Map<String,String>map = new HashMap<>();
 		map.put("userId", userId);
 		map.put("searchItem", searchItem);
 		map.put("searchWord", searchWord);
 		
 		return mapper.getCount(map);
 	}


 	
	
}
