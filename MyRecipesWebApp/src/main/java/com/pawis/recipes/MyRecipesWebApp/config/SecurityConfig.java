package com.pawis.recipes.MyRecipesWebApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pawis.recipes.MyRecipesWebApp.service.UserServiceImpl1;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackages = "com.pawis.recipes.MyRecipesWebApp.dao")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl1 userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
		.and().authenticationProvider(authProvider());
	
		
		//auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		//auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	
	public DaoAuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/Login").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/user/register-new-user").permitAll()
		.antMatchers("/processRegister").permitAll()
		.antMatchers("/login/oauth2/code/github").permitAll()
		.anyRequest().authenticated()
		.and()
		//.oauth2Login()
		//.loginPage("/Login")
		//.and()
		.formLogin()
		.loginPage("/Login")
		.loginProcessingUrl("/Login")
		.defaultSuccessUrl("/user/userList")
		.permitAll()
		.and()
		.rememberMe()
		.and()
		.logout().logoutSuccessUrl("/").permitAll();
		
	}
	

}
