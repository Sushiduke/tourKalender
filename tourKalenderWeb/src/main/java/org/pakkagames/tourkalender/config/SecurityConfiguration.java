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
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public SecurityConfiguration() {
		super();
	}

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers("/admin/**").hasRole("ADMIN") //
				.antMatchers("/user/**").hasRole("USER") //
				.and() //
				.logout().logoutSuccessUrl("/index.html") //
				.and() //
				.formLogin() //
				.loginPage("/login.html") //
				.failureUrl("/error.html") //
				.and() //
				.logout().logoutSuccessUrl("/login?logout") //
				.and() //
				.exceptionHandling() //
				.accessDeniedPage("/403.html") //
				.and() //
				.csrf();
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
