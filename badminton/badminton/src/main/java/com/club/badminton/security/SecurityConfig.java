package com.club.badminton.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService users(PasswordEncoder key) {
		UserDetails admin = User.withUsername("Mithun")
				.password(key.encode("Mithun@123"))
				.roles("ADMIN")
				.build();
		UserDetails user = User.withUsername("Mithun1")
				.password(key.encode("Mithun@123"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.authorizeHttpRequests()
	            .requestMatchers("/admin/**")
	            .hasRole("ADMIN")
	            .anyRequest()
	            .permitAll()
	            .and()
	            .formLogin()
	            .and()
	            .build();
	}
}
