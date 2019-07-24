package global.sesoc.test6.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.test6.dto.Member;

@Repository
public class MemberRepository {

	@Autowired
	SqlSession session;
	
	public int insert(Member member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);

		return mapper.insert(member);
	}

	public Member selectOne(String userid, String userpwd) {
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		
		//1)이방식은 지금 안쓸거임
		/*Member member = mapper.login2(userid, userpwd);*/
		
		
		//2)맵을이용한게 훨씬 깔끔하고 좋음
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("userpwd", userpwd);
		Member member = mapper.login(map);
		
		
		return member;
	}

	public int dropOut(String userid, String userpwd) {
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		Map<String,String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("userpwd", userpwd);
		int result = mapper.delete(map);
		
		return result;
	}

	public int modify(Member member) {
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		return mapper.update(member);
	}

	
}
