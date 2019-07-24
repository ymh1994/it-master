package global.sesoc.ymh.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import global.sesoc.ymh.dto.Todo;

public interface TodoListMapper {

	public int insert(Todo todo);
	
	public ArrayList<Todo> selectAll(Map<String,String>map,RowBounds rb);
	
	public int delete(int todoSeq);
	
	public int getCount(Map<String,String>map);
	
}
