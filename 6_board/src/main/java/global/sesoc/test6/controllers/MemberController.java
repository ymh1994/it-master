package global.sesoc.test6.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import global.sesoc.test6.dao.MemberRepository;
import global.sesoc.test6.dto.Member;

@Controller
public class MemberController {
	@Autowired
	MemberRepository repo;
	
	/**
	 * 회원가입 화면 요청 : GET 요청
	 * @return 회원가입을 할 수 있는 signup.jsp로 포워딩
	 */
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup() {
		
		
		return "member/signup";
	}
	/**
	 * 회원가입 처리 요청 : POST 요청
	 * @return 회원가입 처리 후 메인 화면으로 이동
	 */
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signProcess(Member member) {
		System.out.println(member);
		
		int result = repo.insert(member);
	
		if(result==1){
			return "index";
		}
		else{
			return "member/signup";
		}
		
	}
	
	
	/**
	 * 회원로그인 화면요청 : GET 요청
	 * @return 회원로그인 화면으로 이동
	 */
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String signin() {
		
		return "member/login";
	}
	
	
	/**
	 * 회원로그인 처리요청 : POST 요청
	 * @return 회원로그인  처리로이동
	 */

	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public String signinProcess(String userid,String userpwd,HttpSession session) {
		
		System.out.println(userid+""+userpwd);
		
		Member member = repo.selectOne(userid,userpwd);
		System.out.println("로그인 여부"+member);
		
		//HttpSession에 로그인한정보를 저장   -->이건 SqlSession과는 다른거임!!!!!!!!!!!!!!!!
		//한사람의 유저에게 할당된,브라우저하나에게 할당된 메모리공간.
		if(member!=null){
			session.setAttribute("loginId",member.getUserid());
            session.setAttribute("loginName", member.getUsername());;
		}
		
		return "redirect:/";           //root(/)를 다시요청하는것.
	}
	
	@RequestMapping(value="/dropout", method=RequestMethod.GET)
	public String dropout() {
		
		return "member/dropout";           //root(/)를 다시요청하는것.
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
			session.invalidate(); //한번에 다지움
		
		return "redirect:/";           //root(/)를 다시요청하는것.
	}
	
	
	@RequestMapping(value="/dropout", method=RequestMethod.POST)
	public String dropoutProcess(String userid,String userpwd,HttpSession session) {
		System.out.println(userid+""+userpwd);
		String sessionId=(String)session.getAttribute("loginId");
		
	
		
		if(sessionId.equals(userid)){
			int result = repo.dropOut(userid,userpwd);
			//session.setAttribute("loginId", null);
			session.invalidate();
		}
		
		return "redirect:/";           //root(/)를 다시요청하는것.
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(Model model,HttpSession session) {
		String sessionId=(String)session.getAttribute("loginId");
		model.addAttribute("loginId",sessionId);
		return "member/modify";           //root(/)를 다시요청하는것.
	}
	

	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyProcess(Member member,Model model) {

		int result=repo.modify(member);
		
		if(result==1){	
			model.addAttribute("result",result);
			return "index"; 
		}
		else{
			return "member/modify";
		}
		          
	}

	
	
}
