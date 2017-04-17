package org.pakkagames.tourkalender.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class ExampleSecurityConfiguration extends WebSecurityConfigurerAdapter {

	public ExampleSecurityConfiguration() {
		super();
	}

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers( //
						"/", //
						"/favicon.ico", //
						"/resources/**", //
						"/signin", //
						"/signup" //
				).permitAll() //
				.antMatchers("/**").authenticated() //
				.and() //
				.formLogin() //
				.loginPage("/signin.html") //
				.failureUrl("signin.html?error=1") //
				.and() //
				.logout().logoutUrl("/logout"); //
		;
	}

	@Autowired
	protected void configAuthentication(final AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication() //
				.dataSource(dataSource) //
				.usersByUsernameQuery( //
						"select username,password, enabled from users where username=?") //
				.authoritiesByUsernameQuery( //
						"select username, role from user_roles where username=?");
	}

}
