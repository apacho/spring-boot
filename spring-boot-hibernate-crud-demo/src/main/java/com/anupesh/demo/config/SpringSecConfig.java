package com.anupesh.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_LIST = { //
			"/v2/api-docs", //
			"/configuration/ui", //
			"/swagger-resources", //
			"/configuration/security", //
			"/swagger-ui.html", //
			"/webjars/**" //
	};

	/*
	 * @Override protected void configure(HttpSecurity httpSecurity) throws
	 * Exception { httpSecurity.authorizeRequests().antMatchers("/",
	 * "/swagger-resources").permitAll(); httpSecurity.csrf().disable();
	 * httpSecurity.headers().frameOptions().disable(); }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
				.password("{noop}admin").roles("USER", "ADMIN");
	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests().antMatchers(AUTH_LIST).authenticated().and().
	 * httpBasic(); }
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * auth.inMemoryAuthentication().withUser("admin").password("{noop}password").
	 * roles("USER"); }
	 */

}