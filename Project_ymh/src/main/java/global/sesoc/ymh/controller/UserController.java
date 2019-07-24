package global.sesoc.ymh.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import global.sesoc.ymh.dao.UserRepository;
import global.sesoc.ymh.dto.User;
@Controller
public class UserController {

	
	@Autowired
	UserRepository repo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Locale locale, Model model) {
		
		return "user/signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupProcess(User user,Model model) {
		System.out.println(user);
		int result = repo.insertUser(user);
		
		return "redirect:/";
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(User user,Model model) {
		
		return "user/login";
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(User user,Model model,RedirectAttributes ratt,HttpSession session) {
	
		User USER = repo.login(user);
		if(USER!=null){
		session.setAttribute("loginId", user.getUserId());
		session.setAttribute("userName", USER.getUserName());
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String login(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/dropout", method = RequestMethod.GET)
	public String dropout() {
		
		return "user/dropout";
	}
	
	@RequestMapping(value = "/dropout", method = RequestMethod.POST)
	public String dropoutProcess(User user,HttpSession session) {
	        	
    	 int result = repo.dropout(user);	  
		if(result!=0){
			session.invalidate();
		}
		return "redirect:/";
	}
	
	
}
