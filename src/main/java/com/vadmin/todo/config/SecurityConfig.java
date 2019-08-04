package com.vadmin.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("webuser").password("{noop}web123").roles("ADMIN");
	}
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		   .csrf().disable()
           .authorizeRequests()
            .antMatchers("/resources/**", "/webjars/**","/css/**","/js/**","/img/**").permitAll()
               .antMatchers("/login").permitAll()
               .anyRequest().authenticated()
               .and()
			   .formLogin()
			   .loginPage("/login").permitAll();
			
			
		http
		.rememberMe()
		.rememberMeCookieName("remember-me")
		.tokenValiditySeconds(10000000);
			
		http
			.logout()
			.logoutUrl("/logout");
			
	}

}
