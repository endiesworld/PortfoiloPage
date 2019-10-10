package com.leworks.PortfolioPage.Service;




import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.leworks.PortfolioPage.DAO.MyRepository;
import com.leworks.PortfolioPage.Model.MyUserDetails;
import com.leworks.PortfolioPage.Model.Users;

@Service
public class MyServiceClass implements UserDetailsService { 
	
	private MyRepository myRepository ;
	
	public MyServiceClass(MyRepository myRepository) {
		this.myRepository = myRepository ;
	} 
	
	public void saveUser(Users user) {
		myRepository.save(user) ;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = myRepository.findByUsername(username);
		MyUserDetails userDetails = new MyUserDetails(user) ;
		System.out.println(user);
		return userDetails ;
	}
	 
}
