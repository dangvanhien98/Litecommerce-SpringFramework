//package com.litecommerce.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//public class WebSecurityConfig {
//
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(User.withUsername("user@gmail.com").password(bCryptPasswordEncoder.encode("123"))
//				.roles("USER").build());
//		manager.createUser(User.withUsername("admin@gmail.com").password(bCryptPasswordEncoder.encode("123"))
//				.roles("ADMIN").build());
//		return manager;
//	}
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf()
//		.disable()
//		.authorizeRequests()
//			.antMatchers("/admin").hasAnyRole("ADMIN", "USER")
////			.anyRequest().authenticated()
//			.and()
//		.formLogin()
//			.defaultSuccessUrl("/admin")
//			.permitAll()
//			.and()
//			.logout()
//			.permitAll();
////		.antMatchers(HttpMethod.DELETE)
////		.hasRole("USER")
////		.antMatchers("/admin")
////		.hasAnyRole("ADMIN")
////		.antMatchers("/login")
////		.anonymous()
////		.anyRequest()
////		.authenticated()
////		.and()
////		.formLogin()
////		.defaultSuccessUrl("/")
////		.and().logout();
//		
////	      .and()
////	      .httpBasic()
////	      .and()
////	      .sessionManagement()
////	      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		return http.build();
//	}
//
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().antMatchers("/resources/**");
//	}
//
//}