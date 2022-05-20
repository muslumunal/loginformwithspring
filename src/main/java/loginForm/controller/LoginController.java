package loginForm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import loginForm.dao.LoginDao;
import loginForm.model.User;

@Controller
public class LoginController {

	@Autowired
	private LoginDao loginDao;
	
	@GetMapping("/login")
	public String loginForm(){
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
		User user = loginDao.checkLogin(username, password);
		if(user != null) {
			session.setAttribute("username", username);
			return "redirect:loginsuccess";
		}else {
			return "redirect:login";
		}
	}
	
	@GetMapping("/loginsuccess")
	public String loginSuccess(HttpSession session){
		Object username = session.getAttribute("username");
		if(username == null) {
			return "redirect:login";
		}
		else {
			return "loginsuccess";
		}
		
	}
}
