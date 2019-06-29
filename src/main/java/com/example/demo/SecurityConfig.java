package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled" + " from users where username=?")
				.authoritiesByUsernameQuery("select username, authority " + "from authorities where username=?")
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http         
    //     // .headers()
    //     //  .frameOptions().sameOrigin()
    //     //  .and()
    //        .authorizeRequests()
    //         .antMatchers("/resources/**", "/webjars/**","/css/**","/js/**","/img/**").permitAll()
    //            .antMatchers("/login").permitAll()
    //            .antMatchers("/error").permitAll()
    //            .antMatchers("/autologin/**").permitAll()
    //            .antMatchers("/admin/**").hasAuthority("ADMIN")
    //            .antMatchers("/**").hasAnyAuthority("ADMIN","USER")
    //            .anyRequest().authenticated()
    //            .and()
    //        .formLogin()
    //            .loginPage("/login")
    //            .defaultSuccessUrl("/kind-choose")
    //            .failureUrl("/login?error")
    //            .usernameParameter("loginId")
    //            .passwordParameter("password")
    //            .permitAll()
    //            .and()
    //             .rememberMe()
    //             .tokenRepository(this.persistenceTokenRepository())
    //             .tokenValiditySeconds(10000000)
    //             .alwaysRemember(true)
    //             .and()
    //        .logout()
    //         // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    //         .logoutSuccessUrl("/login?logout")
    //         .permitAll()
    //         .and()
    //        .exceptionHandling()
    //        .accessDeniedPage("/error")
    //        .and()
    //        .csrf().disable();
    // }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
           .authorizeRequests()
            .antMatchers("/resources/**", "/webjars/**","/css/**","/js/**","/img/**").permitAll()
               .antMatchers("/login").permitAll()
               .antMatchers("/**").hasAnyAuthority("ROLE_ADMIN","USER")
               .anyRequest().authenticated()
               .and()
           .formLogin()
               .loginPage("/login")
               .defaultSuccessUrl("/todo")
               .failureUrl("/login?error")
               .usernameParameter("username")
               .passwordParameter("password")
               .permitAll();
			
			//remember me configuration
			http
				.rememberMe().tokenRepository(this.tokenRepository())
				.tokenValiditySeconds(10000000).alwaysRemember(true);
			
			http
				.logout()
				.logoutUrl("/logout");
			
	}

	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		return jdbcTokenRepositoryImpl;
	}
}
