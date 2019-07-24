package global.sesoc.ymh.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.ymh.dto.User;

@Repository
public class UserRepository {

	@Autowired
	SqlSession session;
	
public int insertUser(User user){
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		System.out.println(user);
		return mapper.insertUser(user);
		
	}

public User login(User user) {
	
	UserMapper mapper = session.getMapper(UserMapper.class);
	return mapper.login(user);
	
}

public int dropout(User user) {
	UserMapper mapper = session.getMapper(UserMapper.class);
	return mapper.dropout(user);
}

	
}
