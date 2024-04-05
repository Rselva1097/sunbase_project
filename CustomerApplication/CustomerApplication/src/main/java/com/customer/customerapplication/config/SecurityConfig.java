package com.customer.customerapplication.config;


import com.customer.customerapplication.security.JwtAuthenticationEntryPoint;
import com.customer.customerapplication.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	private UserDetailsService userDetailService;
	
	private JwtAuthenticationEntryPoint authenticationentrypoint;
	
	private JwtAuthenticationFilter authenticationFilter;
	
	
	
	public SecurityConfig(UserDetailsService userDetailService,
                          JwtAuthenticationEntryPoint authenticationentrypoint,
                          JwtAuthenticationFilter authenticationFilter) {
		this.userDetailService = userDetailService;
		this.authenticationentrypoint= authenticationentrypoint;
		this.authenticationFilter = authenticationFilter;
	}
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean 
	public AuthenticationManager authenticateionManager(AuthenticationConfiguration config) 
			throws Exception {
	    return config.getAuthenticationManager();
	}
	
	@Bean
	  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()

                ).exceptionHandling( exception -> exception
                        .authenticationEntryPoint(authenticationentrypoint)
                ).sessionManagement( session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
