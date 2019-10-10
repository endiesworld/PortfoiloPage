package com.leworks.PortfolioPage.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.leworks.PortfolioPage.Service.MyServiceClass;


@Configuration
@EnableWebSecurity
public class MySecurityConfigClass extends WebSecurityConfigurerAdapter {
	
	private MyServiceClass myServiceClass ;
		
	public MySecurityConfigClass (MyServiceClass myServiceClass) {
		this.myServiceClass = myServiceClass ; 
	  }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider()) ;
	}
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception { 
	  http
	  		.authorizeRequests().antMatchers("/emmanuelokoro").permitAll()
	  		.antMatchers("/jsFiles/**","/cssFolder/**","/images/**","/webjars/**").permitAll()
	  		.antMatchers("/emmanuelokoro/signupPage").permitAll()
	  		.antMatchers("/emmanuelokoro/memoryGame").authenticated()
	  		//.antMatchers("/").hasRole("USER")
	  		.and()
	  		.formLogin()
	  		.loginProcessingUrl("/login")
	  		.loginPage("/emmanuelokoro/login").permitAll()
	  		.usernameParameter("username")
	  		.passwordParameter("password");
	  		//.successHandler(successHandler()) ;
	  		http.csrf().disable();
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
    }
	
	
	/*
	 * @Bean public AuthenticationSuccessHandler successHandler() {
	 * SimpleUrlAuthenticationSuccessHandler handler = new
	 * SimpleUrlAuthenticationSuccessHandler();
	 * handler.setAlwaysUseDefaultTargetUrl(true) ; 
	 * handler.onAuthenticationSuccess(HttpServletRequest request, response,
	 * authentication);
	 * handler.setUseReferer(true);
	 * //System.out.println(handler.getTargetUrlParameter()) return handler; }
	 */
	 

	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.myServiceClass);
        return daoAuthenticationProvider;
    }
}
