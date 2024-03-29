package com.uniovi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import com.uniovi.services.RolesService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private String User = RolesService.getRoles()[0];
	private String Admin = RolesService.getRoles()[1];

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}

	// /login
	// /signup
	// /user/add
	// /user/details/{id}
	// /user/edit/{id}
	// /user/delete/{id}
	// /user/list
	// /user/send
	// /home
	// /
	// /friend/invitationlist
	// /friend/accept
	// /friend/friendlist
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/css/**", "/img/**", "/script/**", "/", "/signup", "/login/**", "/403").permitAll()
			.antMatchers("/user/list").hasAnyAuthority(User,Admin)
			.antMatchers("/user/add").hasAuthority(Admin)
			.antMatchers("/user/details/*").hasAuthority(Admin)
			.antMatchers("/user/edit/*").hasAuthority(Admin)
			.antMatchers("/user/delete/*").hasAuthority(Admin)
			.antMatchers("/user/send").hasAnyAuthority(User,Admin)
			.antMatchers("/home").hasAnyAuthority(User,Admin)
			.antMatchers("/friend/invitationlist").hasAnyAuthority(User,Admin)
			.antMatchers("/friend/accept").hasAnyAuthority(User,Admin)
			.antMatchers("/friend/friendlist").hasAnyAuthority(User,Admin)
			.anyRequest().authenticated()
				.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/home")
			.and()
		.logout()
			.permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}