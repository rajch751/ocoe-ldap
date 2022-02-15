package com.uob.ocoe.ldap.ocoeldap.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.uob.ocoe.ldap.ocoeldap.filter.JwtFilter;





@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    
	 @Autowired
	 private JwtFilter jwtFilter;

	 	@Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	
	    //	http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
		
		  http.csrf() .disable() .authorizeRequests() .antMatchers("/v1/login/*")
		  .permitAll() .anyRequest() .authenticated() .and() .sessionManagement()
		  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		  http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 
	    	
		/*
		 * http.authorizeRequests()
		 * .antMatchers("/secure/man/**").access("hasRole('MANAGERS')")
		 * .antMatchers("/secure/dev/**").access("hasRole('DEVELOPERS')")
		 * .and().formLogin() .loginPage("/login") .loginProcessingUrl("/appLogin")
		 * .usernameParameter("username") .passwordParameter("password")
		 * .defaultSuccessUrl("/secure/dev") .and().logout() .logoutUrl("/appLogout")
		 * .logoutSuccessUrl("/login") .and().exceptionHandling()
		 * .accessDeniedPage("/accessDenied");
		 */
	    } 
		
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.ldapAuthentication().userDnPatterns("cn={0},ou=User").groupSearchBase("ou=Group")
					.contextSource(contextSource()).passwordCompare().passwordEncoder(new LdapShaPasswordEncoder())
					.passwordAttribute("userPassword");
		}

		@Bean
		public DefaultSpringSecurityContextSource contextSource() {
			return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:10389/"),
					"dc=example,dc=com");
		}
	
	
}
