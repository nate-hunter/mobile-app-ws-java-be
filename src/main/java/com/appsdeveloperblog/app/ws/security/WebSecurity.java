package com.appsdeveloperblog.app.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appsdeveloperblog.app.ws.service.UserService;

// This class will define at least one method, eg a signup method, that users can use to create a new account.
// The method will be public and all other web service entry points will be protected and will require authorization/authentication
// Because this class is a special class it will need to contain some configuration - annotation to enable web security - and extend an adapter

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserService userDetailsService;		// Interface given from Spring framework;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;	// Responsible for encoding our password;
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		
	}
	
	// Because we are extending WebSecurityConfigurerAdapter, we need to override a couple methods, below:
	
	// configure() with HttpSecurity argument is needed to configure some of the web service entry points in our application as public and some as protected
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        	.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
        	.permitAll()
        	.anyRequest().authenticated().and()
        	.addFilter(getAuthenticationFilter())
        	.addFilter(new AuthorizationFilter(authenticationManager()))
        	.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    
    public AuthenticationFilter getAuthenticationFilter() throws Exception {
    	final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
    	filter.setFilterProcessesUrl("/users/login");
    	return filter;
    }

}
