package fi.haagahelia.Bookstore;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity // enables Spring Security web security support
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// WebSecurityConfig class contains method configure(HttpSecurity)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()  // pages permitted to all user
				.antMatchers("/admin/**").hasRole("ADMIN") // pages open to "ADMIN" role only when it starts with /admin
				.anyRequest().authenticated()  // secure page paths: anyRequest();
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()	// login page path to /login
				.and()
			.logout()
				.permitAll();
	}
}
