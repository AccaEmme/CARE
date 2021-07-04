package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	

	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
	
	/*/hello admin --> puo farlo solo admin e devo fare prima una register poi un authenticate*/
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
<<<<<<< HEAD
		.antMatchers("/helloadmin").hasRole("ADMINISTRATOR")
=======
		.antMatchers(/*"/register"*/).hasRole("ADMINISTRATOR")
>>>>>>> c6d2e2e5bf862f548bfd0dc74666b22c80f0b524
		.antMatchers("request/add").hasAnyRole("OFFICER")
<<<<<<< HEAD
		.antMatchers("/bloodbag/add","/bloodbag/use/{serial}").hasAnyRole("STOREMANAGER")
		.antMatchers("/bloodbag/central/add","/bloodbag/use/{serial}").hasAnyRole("CENTRAL_STOREMANAGER")
		.antMatchers("/bloodbag/import", "/bloodbag/add","/authenticate", "logger/add","/request/add","/request/accept","/user/delete/{username}","/register","user/update/username/id/{id}/{username}","/bloodbag/import").permitAll().anyRequest().authenticated()
=======
		.antMatchers("/bloodbag/add").hasAnyRole("STOREMANAGER")
		.antMatchers("/bloodbag/central/add").hasAnyRole("CENTRAL_STOREMANAGER")
		.antMatchers("/bloodbag/use/{serial}").hasAnyRole("STOREMANAGER","CENTRAL_STOREMANAGER")	//linea aggiunta
<<<<<<< HEAD
		.antMatchers("/authenticate", "logger/add","/request/add","/request/accept","/user/delete/{username}","/register","user/update/username/id/{id}/{username}","/bloodbag/import").permitAll().anyRequest().authenticated()
=======
		.antMatchers("/bloodbag/add","/authenticate", "logger/add","/request/add","/request/accept","/user/delete/{username}","/register","user/update/username/id/{id}/{username}","/bloodbag/import").permitAll().anyRequest().authenticated()
>>>>>>> aca4eb90c7d699d878cf6558edbf946c0cd62680
>>>>>>> c6d2e2e5bf862f548bfd0dc74666b22c80f0b524
		.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
		and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	  @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowCredentials(true);
	        configuration.setAllowedMethods(Arrays.asList("GET","POST" ,"PUT", "DELETE", "OPTIONS", "HEAD"));
	        configuration.setAllowedHeaders(Arrays.asList( "CSRF-Token","X-Requested-By", "Authorization", "Content-Type"));
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	


}
