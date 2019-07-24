package global.sesoc.ymh.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import global.sesoc.ymh.util.PageNavigator;
import global.sesoc.ymh.dao.TodoListRepository;
import global.sesoc.ymh.dto.Todo;

@Controller
public class TodoListController {

	@Autowired
	TodoListRepository repo;
	
	
	private static final Logger logger = LoggerFactory.getLogger(TodoListController.class);
	@RequestMapping(value = "/todolist", method = RequestMethod.GET)
	public String todolist(HttpSession session,Model model) {
		
         
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일(E)");
		String day=sdf.format(date);
		model.addAttribute("serverTime", day);
		
		
		return "todolist/todolist";
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertTodolist(HttpSession session,Todo todo) {
		String loginId=(String)session.getAttribute("loginId"); 
		todo.setUserId(loginId);
		
		System.out.println(todo);
		   int result = repo.insert(todo);
		
		return "redirect:/todolist";
	}
	
	@RequestMapping(value = "/allday", method = RequestMethod.GET)
	public String selectAll(Model model,HttpSession session,@RequestParam(value="searchItem",defaultValue="todoData")String searchItem,
			@RequestParam(value="searchWord",defaultValue="")String searchWord
			,@RequestParam(value="currentPage",defaultValue="1")int currentPage) {


		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일(E)");
		String day=sdf.format(date);
		model.addAttribute("serverTime", day);
		
		
	   String loginId=(String)session.getAttribute("loginId");
		
		int totalRecordCount = repo.getCount(loginId,searchItem, searchWord);
		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
		List<Todo> tList = repo.selectAll(loginId,searchItem, searchWord, navi.getStartRecord(), navi.getCountPerPage());
		
	   
	   
	   
		for(int i=0;i<tList.size();i++){
			if(tList.get(i).getImportance().equals("poor")){
				tList.get(i).setImportance("하");
			}
			else if(tList.get(i).getImportance().equals("average")){
				tList.get(i).setImportance("중");
			}
			else if(tList.get(i).getImportance().equals("good")){
				tList.get(i).setImportance("상");
			}
		}
	
	
		model.addAttribute("list",tList);
		model.addAttribute("navi",navi);
		
		model.addAttribute("searchItem",searchItem);
		model.addAttribute("searchWord",searchWord);
		      model.addAttribute("tList",tList);
		
		      
		return "todolist/todolist";
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteTodoList(int todoSeq,Model model) {


		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일(E)");
		String day=sdf.format(date);
		model.addAttribute("serverTime", day);
		
		int result = repo.delete(todoSeq);
		return "redirect:/todolist";
	}
		
	
	@RequestMapping(value = "/backup", method = RequestMethod.GET)
	public String backup(Model model,HttpSession session,@RequestParam(value="searchItem",defaultValue="todoData")String searchItem,
			@RequestParam(value="searchWord",defaultValue="")String searchWord,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage) {
 

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일(E)");
		String day=sdf.format(date);
		model.addAttribute("serverTime", day);
		String loginId=(String)session.getAttribute("loginId");
		int totalRecordCount = repo.getCount(loginId,searchItem, searchWord);
		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
		  
ArrayList<Todo>tList = repo.selectAll(loginId,searchItem,searchWord,navi.getStartRecord(), navi.getCountPerPage());
		
			
		
			
			
			File file = new File("D:/backup.txt");
		
				FileWriter fw=null;
				BufferedWriter bw=null;
				
				
				try {
					fw = new FileWriter(file,false);
					 bw=new BufferedWriter(fw);
					    String a ="";
					    for(int i=0;i<tList.size();i++){
					    	a=tList.get(i)+"\n";
					    	 bw.write(a+"\r\n");
					    	 System.out.println(a);
					    }
					   bw.flush();
						
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						if(fw!=null&&bw!=null){
						fw.close();
						bw.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			   
		
		return "redirect:/todolist";
	}
	
	

	
	
}
