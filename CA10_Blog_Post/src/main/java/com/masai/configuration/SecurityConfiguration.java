package com.masai.configuration;

import java.util.Arrays;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity
			.sessionManagement(
					session ->
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.cors(cors->{
				cors.configurationSource(new CorsConfigurationSource() {
					
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration cfg = new CorsConfiguration();
						cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
						cfg.setAllowedMethods(Collections.singletonList("*"));
						cfg.setAllowCredentials(true);
						cfg.setAllowedHeaders(Collections.singletonList("*"));
						cfg.setExposedHeaders(Arrays.asList("Authorization"));
						return cfg;
					}
				});
			})
			.authorizeHttpRequests(auth->{
				auth
				  .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
				  .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
//				  .requestMatchers(HttpMethod.GET, "/users/getAllUsers").hasAnyRole("ADMIN","USER")
				  .requestMatchers(HttpMethod.GET, "/users/getAllUsers").permitAll()
				  .requestMatchers(HttpMethod.GET, "/users/getUserByEmail/{email}").permitAll()
//				  .requestMatchers(HttpMethod.DELETE, "/users/deleteUserById/{userId}").hasAnyRole("ADMIN")
				  .requestMatchers(HttpMethod.DELETE, "/users/deleteUserById/{userId}").permitAll()
				  .requestMatchers(HttpMethod.POST, "/post/addPost").permitAll()
				  .requestMatchers(HttpMethod.GET, "/post/getPostById/{postId}").permitAll()
				  .requestMatchers(HttpMethod.GET, "/post/getAllPost").permitAll()
				  .requestMatchers(HttpMethod.DELETE, "/post/deletePostById/{postId}").permitAll()
				  .requestMatchers(HttpMethod.POST, "/post/modifyPost/{postId}").permitAll()
				  .requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
				       .anyRequest()
				       .authenticated();
			})
			.csrf(csrf->csrf.disable())
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
	

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
