package com.ajith;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity

public class SecurityConfig {

	@Bean
	UserDetailsService userdetail(PasswordEncoder encoder) {
		UserDetails admin = User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build();
		UserDetails user = User.withUsername("user").password(encoder.encode("user")).roles("USER").build();
		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults());
//				return http.build();

		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/**").authenticated().and().formLogin().and().httpBasic()
				.and().build();
	}

}
