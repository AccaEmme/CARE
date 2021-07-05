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
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	/*/hello admin --> puo farlo solo admin e devo fare prima una register poi un authenticate*/
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
        .antMatchers("/helloadmin").hasRole("ADMINISTRATOR")
        
    	.antMatchers("request/add").hasAnyRole("OFFICER")
    	
		.antMatchers("/bloodbag/add","/bloodbag/use/{serial}").hasAnyRole("STOREMANAGER")
		
		.antMatchers("/bloodbag/central/add","/bloodbag/use/{serial}").hasAnyRole("CENTRAL_STOREMANAGER")
		
		.antMatchers("/authenticate", 	"logger/add",	"/request/add",
					 "/request/accept",	"/user/delete/{username}",
					 "/register",		"user/update/username/id/{id}/{username}",
					 "/bloodbag/import").permitAll().anyRequest().authenticated()
		
		.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
		and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
        http.cors(c -> {
            CorsConfigurationSource cs = r -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowedOriginPatterns(Arrays.asList("*"));
                cc.setAllowCredentials(true);
                cc.setAllowedMethods(Arrays.asList("GET","POST" ,"PUT", "DELETE", "OPTIONS", "HEAD"));
                cc.setAllowedHeaders(Arrays.asList( "CSRF-Token","X-Requested-By", "Authorization", "Content-Type"));
        
                return cc;
            };

            c.configurationSource(cs);
        });
		
	}
	
}
