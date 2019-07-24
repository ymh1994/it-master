package global.sesoc.test6.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.test6.dto.Board;
import global.sesoc.test6.dto.Reply;

@Repository
public class BoardRepository {

	@Autowired
	SqlSession session;
	
	
	public int insert(Board board){
		
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.insert(board);
	}


/*	public List<Board> selectAll_backup(String searchItem, String searchWord, int srow,int erow) {
	   BoardMapper mapper = session.getMapper(BoardMapper.class);
	   
	   Map<String,Object>map = new HashMap<>();
		map.put("searchItem", searchItem);
		map.put("searchWord", searchWord);
		map.put("srow", srow);
		map.put("erow", erow);
		List<Board>list = mapper.selectAll(map);
		return list;
	}
	*/
	
	public List<Board> selectAll(String searchItem, String searchWord, int startRecord, int countPerPage) {
		
	
		RowBounds rb = new RowBounds(startRecord,countPerPage);
		BoardMapper mapper = session.getMapper(BoardMapper.class);  //reflection
		Map<String,String>map = new HashMap<>();
		map.put("searchItem", searchItem);
		map.put("searchWord", searchWord);
		System.out.println(mapper.selectAll(map,rb).size());
		
		
		return mapper.selectAll(map,rb);
	}
	


	public Board selectOne(int boardseq) {
		
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		Board board = mapper.selectOne(boardseq);
		return board;
	}


	public int boardDelete(int boardseq) {
		
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.delete(boardseq);
	}


	public int boardUpdate(Board board) {
		
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.update(board);
	}


	/*public int getBoardCount(String searchItem, String searchWord,int srow,int erow) {  //이건 구버전 0717수업전꺼 백업본

		BoardMapper mapper = session.getMapper(BoardMapper.class);
		Map<String,Object>map = new HashMap<>();
		map.put("searchItem", searchItem);
		map.put("searchWord", searchWord);
		map.put("srow", srow);
		map.put("erow", erow);
		int total =  mapper.getBoardCount(map);
		
		return total;
	}*/


	public int getBoardCount(String searchItem, String searchWord) {  //이게 페이지네비게이션을 사용한 방법
		
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		Map<String,String>map = new HashMap<>();
		map.put("searchItem", searchItem);
		map.put("searchWord", searchWord);
		int total =  mapper.getBoardCount(map);
		
		return total;
		
	}

    public int viewcountUp(Board board){
    	BoardMapper mapper = session.getMapper(BoardMapper.class);
    	
        return mapper.viewcountUp(board);   	
    }
	
	
    public int insertReply(Reply reply){
    	int result=0;
    	
    	BoardMapper mapper = session.getMapper(BoardMapper.class);
    	try{
    	result=mapper.insertReply(reply);
    	System.out.println(reply);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return result;
    	}
    	return result;
    }
 
    public List<Reply> selectReply(int boardseq){
    	
    	List<Reply>result = null;
    	BoardMapper mapper = session.getMapper(BoardMapper.class);
    	try{
    	result = mapper.selectReply(boardseq);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return result;
    	}
    	return result;
    }
    
    public int deleteReply(int replyseq){
    	int result=0;
    	BoardMapper mapper = session.getMapper(BoardMapper.class);
    	try{
    	result = mapper.deleteReply(replyseq);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return result;
    	}
    	return result;
    }


	public int updateReply(Reply reply) {
         int result=0;
         BoardMapper mapper = session.getMapper(BoardMapper.class);
        
         try{
         result = mapper.updateReply(reply);
         }
         catch(Exception e){
        	 e.printStackTrace();
        	 return result;
         }
		
		return result;
	}
    
	
	   public int updateDeleteReply(int replyseq){
	    	int result=0;
	    	BoardMapper mapper = session.getMapper(BoardMapper.class);
	    	try{
	    	result = mapper.updateDeleteReply(replyseq);
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    		return result;
	    	}
	    	return result;
	    }
	
	   
	   
	   public int deleteAllReply(int replyseq){
	    	int result=0;
	    	BoardMapper mapper = session.getMapper(BoardMapper.class);
	    	try{
	    	result = mapper.deleteAllReply(replyseq);
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    		return result;
	    	}
	    	return result;
	    }
	
}
