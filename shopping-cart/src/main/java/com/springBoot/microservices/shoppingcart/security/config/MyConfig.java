package com.springBoot.microservices.shoppingcart.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public UserDetailsService userDetailsService1() {
        return new UserDetailsServiceImpl();
    }
     
    /*@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
    
    @SuppressWarnings("deprecation")
	@Bean
    public PasswordEncoder passwordEncoder()
    {
    return NoOpPasswordEncoder.getInstance();   
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService1());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/customer/**").hasAuthority("User")
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.antMatchers("/getUser/**").hasAnyAuthority("User")
        .and()
        .httpBasic().and()
        .authorizeRequests().anyRequest().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/403")
        ;
		}
    
   
}
