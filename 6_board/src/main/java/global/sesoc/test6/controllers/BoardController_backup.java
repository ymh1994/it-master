package global.sesoc.test6.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import global.sesoc.test6.dao.BoardRepository;
import global.sesoc.test6.dto.Board;


public class BoardController_backup {
  //백업용이라 컨트롤러 @Controller 지워놨으니 나중에 다시 사용할시 달도록
	@Autowired
	BoardRepository repo;
	
	
	
	
	@RequestMapping(value="/boardWrite",method=RequestMethod.GET)
	public String boardWrite(Model model){  //boardWrite 화면요청
		
		Date today = new Date();
		model.addAttribute("today",today);
		
		System.out.println("글쓰기화면을 요청함.");
		return "/board/boardWrite";
		
	}
	
	
	@RequestMapping(value="/boardWrite",method=RequestMethod.POST)
	public String boardWriteProcess(Board board,HttpSession session){  

		String userId=(String)session.getAttribute("loginId");
		board.setUserid(userId);
		
		int result = repo.insert(board);
		
		
		return "redirect:/boardList";
		
	}
	
	
	
	@RequestMapping(value="/boardDetail",method=RequestMethod.GET)
	public String boardDetail(int boardseq,Model model){  
           		
		Board board=repo.selectOne(boardseq);
		
		model.addAttribute("board",board);
		
		return "board/boardDetail";
		
	}
	
	@RequestMapping(value="/boardDelete",method=RequestMethod.GET)
	public String boardDelete(int boardseq){  
		int result =repo.boardDelete(boardseq);
		
		return "redirect:boardList";   //브라우저한테  받은거를 재요청  /를 쓰면안됨
  		
	}
	
	@RequestMapping(value="/boardUpdate",method=RequestMethod.GET)
	public String boardUpdate(Board board,Model model){
          Board board2=repo.selectOne(board.getBoardseq());
		
		model.addAttribute("board",board2);
		
		return "board/boardupdate";
	}
	
	@RequestMapping(value="/boardUpdate",method=RequestMethod.POST)
	public String boardUpdateProcess(Board board,Model model,RedirectAttributes rttr){    //리다이렉트어트리뷰트를 쓰면 리턴할때 리다이렉트시 값전달같이가능.
		int result =repo.boardUpdate(board);
	
		model.addAttribute("board",board);
		//rttr.addAttribute("boardseq",board.getBoardseq()); 이런식으로도 가능.
		return "redirect:boardDetail";
	}
	
/*	  
	@RequestMapping(value="/boardList",method=RequestMethod.GET)  //이걸로 원래의 전체검색을 아예 조건걸어서 검색도가능하게함
	public String boardSelectAll(@RequestParam(value="searchItem",defaultValue="title")String searchItem,
			@RequestParam(value="searchWord",defaultValue="")String searchWord,Model model,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage){  
		int countPerPage =10;
		
		
		int srow=1+(currentPage-1)*countPerPage; //페이지요청을 1요청시 1 1  2 11
 		int erow=currentPage*countPerPage;//               1 10 2 20
		
		int total = repo.getBoardCount(searchItem,searchWord,srow,erow); //글갯수
		
		//진짜페이지수 : 51
		int totalPages = total/countPerPage;
		totalPages += (total%countPerPage!=0) ? 1 :0;
		
		if(searchItem == null){
			searchItem ="title";  //초기값
			searchWord="";
		}
		
		List<Board>list = repo.selectAll(searchItem,searchWord,srow,erow);
		
		
		model.addAttribute("list",list);
		model.addAttribute("searchItem",searchItem);
		model.addAttribute("searchWord",searchWord);
		model.addAttribute("totalPages",totalPages); //전체 페이지수
	    model.addAttribute("currentPage",currentPage); //현재페이지번호
	    model.addAttribute("countPerPage",countPerPage);
	  
	   
	    return "board/boardList";
	}
	*/
	
	@RequestMapping(value="/return",method=RequestMethod.GET)
	public String boardReturn(){
		return "redirect:/boardList";
	}
	
	
	
	
	
}
