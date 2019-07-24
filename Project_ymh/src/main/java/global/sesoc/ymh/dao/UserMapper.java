package global.sesoc.ymh.dao;

import global.sesoc.ymh.dto.User;

public interface UserMapper {

	
	public int insertUser(User user);

	public User login(User user);
	
	public int dropout(User user);

}
