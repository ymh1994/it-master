package global.sesoc.test6.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import global.sesoc.test6.dto.Member;

public interface MemberMapper {
	public int insert(Member member);
	public int delete(Map<String,String>map);
	//public Member login2(@Param("userid") String userid, @Param("userpwd")String userpwd);
	public Member login(Map<String, String> map);
	public int update(Member member);
	
}
