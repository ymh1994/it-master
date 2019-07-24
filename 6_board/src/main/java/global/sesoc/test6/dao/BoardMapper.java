package global.sesoc.test6.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import global.sesoc.test6.dto.Board;
import global.sesoc.test6.dto.Reply;

public interface BoardMapper {


	public int insert(Board board); //글등록
	public int delete(int boardseq);//삭제
	public int update(Board board);//수정
	public ArrayList<Board> selectAll(Map<String,String>map,RowBounds rb);//전체출력
	public Board selectOne(int boardseq);//한개보기
	public int getBoardCount(Map<String,String> map);//전체 글 갯수
	public int viewcountUp(Board board);
	
	
	public int insertReply(Reply reply);
	
	public List<Reply> selectReply(int boardseq);
	
	public int deleteReply(int replyseq);  //댓글 1개삭제
	public int updateReply(Reply reply);
	
	public int updateDeleteReply(int replyseq); //삭제시 삭제되었습니다를 위해서
	
	
	public int deleteAllReply(int boardseq);//게시물관련 댓글 전체삭제
}
