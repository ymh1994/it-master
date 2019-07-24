package global.sesoc.test6.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import global.sesoc.test6.dao.BoardRepository;
import global.sesoc.test6.dto.Board;
import global.sesoc.test6.dto.Reply;
import global.sesoc.test6.util.FileService;
import global.sesoc.test6.util.PageNavigator;

@Controller
public class BoardController {

	String viewSign=null; //댓글등록,삭제,수정 시에 조회수안올라가도록
	String updateSign=null;
	
	@Autowired
	BoardRepository repo;
	

	
	final String uploadPath="/uploadfile";
	
	
	@RequestMapping(value="/boardWrite",method=RequestMethod.GET)
	public String boardWrite(Model model){  //boardWrite 화면요청
		
		Date today = new Date();
		model.addAttribute("today",today);
		
		System.out.println("글쓰기화면을 요청함.");
		return "/board/boardWrite";
		
	}
	
	
	@RequestMapping(value="/boardWrite",method=RequestMethod.POST)
	public String boardWriteProcess(Board board,HttpSession session,MultipartFile upload){  

		String userId=(String)session.getAttribute("loginId");
		board.setUserid(userId);
		
		//System.out.println("업로드 여부"+upload+"파일크기"+upload.getSize()+"오리지널파일네임"+upload.getOriginalFilename()+"비어있나여부"+upload.isEmpty());
		
		
		
		//2) FileService를 사용한 코드
		String originalfile = upload.getOriginalFilename();
		String savedfile=FileService.saveFile(upload, uploadPath);
		
		board.setOriginalfile(originalfile);
		board.setSavedfile(savedfile);
		
		
		
		//파일 업로드에 관련된 코드   근데 이건 fileservice에 있음
		/*if(!upload.isEmpty()){
			File path = new File(uploadPath);
			if(!path.isDirectory()){
				path.mkdirs();             //upload폴더를 만드는 것.
				
			}
			
		}
		String originalFilename = upload.getOriginalFilename();  //저장시 같은이름이면 안되니까 오리지널이름에 덧붙여서 작업되도록
        String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
		String filename;
		String ext;
		String savedFilename; //손흥민.2019.jpg
		int lastIndex = originalFilename.lastIndexOf('.'); //내가 찾고자 하는걸 뒤에서부터 찾는것 이러면 마지막 .을찾아서 .jpg 앞에다가 uuid를 붙일수 있음
		filename = originalFilename.substring(0, lastIndex);  //파일의 원래명은 .jpg의 앞까지니까
		
		
		//ext의 의미는 .이후의 값을 의미함 .이후에 형식이 있냐없냐에 따라서 형식을 알아서 만들어주기도하고 안하기도하려고 만듬
		if(lastIndex == -1){      //.이 안붙은 파일이 있을수 있으니까 이것도 검사해야함
			ext = "";
		}
		else{
	        ext = "."+originalFilename.substring(lastIndex+1);  // . 형식이 없을때는 
		}
		
		savedFilename = filename + "_"+uuid+ext; //저장될때의 파일명
		System.out.println(originalFilename);
		System.out.println(savedFilename);
		System.out.println(lastIndex+1);
		System.out.println(originalFilename.substring(lastIndex+1));
		//이름이 중복되지 않도록 유일값을 발생시킴.
		//UUID라는 클래스를 이용하면됨.
		
		//파일을 HDD에 저장해야됨.
		String serverFile = uploadPath + "/" +savedFilename;
		try {
			upload.transferTo(new File(serverFile));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.setOriginalfile(originalFilename);
		board.setSavedfile(savedFilename);
		
	
*/		
		int result = repo.insert(board); 
		
		
		return "redirect:/boardList";
		
	}
	
	
	
	@RequestMapping(value="/boardDetail",method=RequestMethod.GET)
	public String boardDetail(int viewcount,int boardseq,Model model,String viewSign,String updateSign,Reply reply){  
           		
		Board board=repo.selectOne(boardseq);
		
		board.setBoardseq(boardseq);
	   if(viewSign==null){
		board.setViewcount(viewcount+1);
	    repo.viewcountUp(board);
	   }		
		model.addAttribute("board",board);
		List<Reply> replyList = repo.selectReply(boardseq);
     
		model.addAttribute("replyList",replyList);
		model.addAttribute("updateSign",updateSign);
	    model.addAttribute("replyseq",reply.getReplyseq());
	   
		
		
		return "board/boardDetail";
		
	}
	
	@RequestMapping(value="/boardDelete",method=RequestMethod.GET)
	public String boardDelete(int boardseq,MultipartFile upload){  
		
		Board board=repo.selectOne(boardseq);
		String savedfile = board.getSavedfile();
		String fullPath =uploadPath + "/" + savedfile;	
			
		/*FileService.deleteFile(uploadPath+"/"+board.getSavedfile());*/ //이렇게 파일 서비스를 이용해서도 가능
		
		File file = new File(fullPath);
	    repo.deleteAllReply(boardseq);
		int result =repo.boardDelete(boardseq);
		if(result!=0){
			file.delete();
		}
	
	
		return "redirect:boardList";   //브라우저한테  받은거를 재요청  /를 쓰면안됨
  		
	}
	
	@RequestMapping(value="/boardUpdate",method=RequestMethod.GET)
	public String boardUpdate(Board board,Model model){
          Board board2=repo.selectOne(board.getBoardseq());
		
		model.addAttribute("board",board2);
		
		return "board/boardupdate";
	}
	
	@RequestMapping(value="/boardUpdate",method=RequestMethod.POST)
	public String boardUpdateProcess(MultipartFile upload,Board board,Model model,RedirectAttributes rttr){    //리다이렉트어트리뷰트를 쓰면 리턴할때 리다이렉트시 값전달같이가능.
	
		Board orgBoard = repo.selectOne(board.getBoardseq());
		
		if(upload==null){ //파일을 업로드하지않은경우
			int result =repo.boardUpdate(board);
		}
		else if(upload.getSize()==0||upload.isEmpty()){//잘못된파일일경우
			int result =repo.boardUpdate(board);
			
		}
		else{//파일을 업로드한 경우
			
			//1.원래 있던 파일 삭제
			//2.새로 첨부한 파일 업로드
			//3.새로 첨부한 파일정보를 DB에 업데이트
			FileService.deleteFile(uploadPath+"/"+orgBoard.getSavedfile()); //1.원래있던파일 삭제
			String savedname=FileService.saveFile(upload, uploadPath);
			board.setSavedfile(savedname);
			board.setOriginalfile(upload.getOriginalFilename());
			
			int result =repo.boardUpdate(board);
			
			
		}
		model.addAttribute("board",board);
		//rttr.addAttribute("boardseq",board.getBoardseq()); 이런식으로도 가능.
		return "redirect:boardDetail?viewcount="+board.getViewcount()+"&boardseq="+board.getBoardseq();
	}
	
	  
	@RequestMapping(value="/boardList",method=RequestMethod.GET)  //이걸로 원래의 전체검색을 아예 조건걸어서 검색도가능하게함
	public String boardSelectAll(@RequestParam(value="searchItem",defaultValue="title")String searchItem,
			@RequestParam(value="searchWord",defaultValue="")String searchWord,Model model,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage){  
		System.out.println(currentPage);
		
		//게시글 전체 개수 조회
		int totalRecordCount = repo.getBoardCount(searchItem, searchWord);
		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
		List<Board> list = repo.selectAll(searchItem, searchWord, navi.getStartRecord(), navi.getCountPerPage());
		
	
		model.addAttribute("list",list);
		model.addAttribute("navi",navi);
		model.addAttribute("searchItem",searchItem);
		model.addAttribute("searchWord",searchWord);
	 
	   
	    return "board/boardList";
	}
	
	
	@RequestMapping(value="/return",method=RequestMethod.GET)
	public String boardReturn(){
		return "redirect:/boardList";
	}
	
	
	//파일 다운로드

	@RequestMapping(value="/download",method=RequestMethod.GET)
	public String download(int boardseq,HttpServletResponse response){  //header정보 조작을 위해 리스폰스를 이용.
		Board board = repo.selectOne(boardseq);
		String savedfile = board.getSavedfile();
		String originalfile = board.getOriginalfile();

		response.setHeader("Content-Disposition", "attachment;filename="+originalfile); //키랑 밸류값   //사용자가 보는게 이거임 사용자는 오리지널이보이고 저장될땐 세이브드로보이게
		
		String fullPath =uploadPath + "/" + savedfile;
		
		FileInputStream filein = null;
		ServletOutputStream fileout =null;  //소켓통신은 못하니까 이걸이용함
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			FileCopyUtils.copy(filein, fileout);
		  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				filein.close();
				fileout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		                 //이건 뷰단을 볼필요가 없어서 리턴을 널로해서 현재화면 유지
		return null;     
		
	}
	
	

	@RequestMapping(value="/insertReply",method=RequestMethod.POST)
	public String insertReply(HttpSession session,Reply reply,Board board){
	   
		
		String userid=(String)session.getAttribute("loginId");
		
		
		reply.setUserid(userid);
		
		int result=repo.insertReply(reply);
		viewSign="vs"; //이게 실행되면 조회수가 안올라가도록
		
		
		return "redirect:/boardDetail?boardseq="+reply.getBoardseq()+"&viewcount="+board.getViewcount()+"&viewSign="+viewSign;
	}
	
	
	
	@RequestMapping(value="/deleteReply",method=RequestMethod.POST)
	public String deleteReply(HttpSession session,int replyseq,int boardseq,Board board){
	
		if (session.getAttribute("loginId") != null) {
			String loginId = (String) session.getAttribute("loginId");
			List<Reply> rList = repo.selectReply(boardseq);
			for (Reply r : rList) {
				if (r.getReplyseq() == replyseq) {
					if (r.getUserid().equals(loginId)) { // 보안을위해서는 이렇게하는게 맞음.
						repo.deleteReply(replyseq);
						viewSign="vs";
					}
				}
			}

		}
		
		return "redirect:/boardDetail?boardseq="+boardseq+"&viewcount="+board.getViewcount()+"&viewSign="+viewSign;
	}
	
	
	
	@RequestMapping(value="/updateReply",method=RequestMethod.POST)
	public String updateReply(HttpSession session,Reply reply,Board board){
         
		repo.updateReply(reply);
		
		
		return "redirect:/boardDetail?boardseq="+board.getBoardseq()+"&viewcount="+board.getViewcount()+"&viewSign="+viewSign;
			
	}

	
	
	
	
	
	
	
	
	
}
