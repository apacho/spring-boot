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

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/", "/swagger-resources").permitAll();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { //DB
	 * auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().
	 * encode("password")).roles("USER").and().withUser("admin")
	 * .password(passwordEncoder().encode("admin")).roles("USER", "ADMIN");
	 * System.out.println(passwordEncoder().encode("password")); }
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests().antMatchers(AUTH_LIST).authenticated().and().
	 * httpBasic(); }
	 * 
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */

}