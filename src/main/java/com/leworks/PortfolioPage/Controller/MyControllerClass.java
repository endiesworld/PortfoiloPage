package com.leworks.PortfolioPage.Controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.leworks.PortfolioPage.Model.Users;
import com.leworks.PortfolioPage.Service.MyServiceClass;


@Controller
@RequestMapping("/emmanuelokoro")
public class MyControllerClass {
	
	private MyServiceClass myServiceClass ;
	private PasswordEncoder passwordEncoder ;
	
	public MyControllerClass (MyServiceClass myServiceClass, PasswordEncoder passwordEncoder) {
		this.myServiceClass =  myServiceClass ;
		this.passwordEncoder = passwordEncoder ;
	}

	@GetMapping("")
	public String getHomePage() {
		System.out.println("I AM IN GET EMMANUEL OKORO HOME METHOD ok! ") ;
		return "index";
	}
	
	@GetMapping("/signupPage")
	public String getSignUpPage() {
		return "signupPage" ;
	}
	
	@PostMapping("/signupPage{Users}")
	public String registerNewUser(@PathVariable("Users") String Users, Users user) {
		String password = user.getPassword() ;
		String encryptedPassword = passwordEncoder.encode(password);
		user.setPassword(encryptedPassword);
		user.setRole("USER");
		user.setPermission("PRIMARY");
		user.setMemoryGameTime("00:00:00");
		myServiceClass.saveUser(user);
		return "redirect:/emmanuelokoro" ;
	}
	
	
    @GetMapping("/login")
    public String getLoginPage() 
    { 
    	System.out.println("YOU HAVE TO LOGIN") ;
    	return "index" ; 
    }
	 
	
	@GetMapping("/memoryGame")
	public String memoryGame() {
		System.out.println("GAME LOADING...") ;
		return "memoryGame" ;
	}
}
