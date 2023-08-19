package cl.trimons.security.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

	@Autowired
	private UserDetailsService uds;
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;	

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder(10);
//	  }
	  
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()
//            .requestMatchers("/admin/**").hasRole("ADMIN")
//        	.requestMatchers("/**").hasRole("USER")
        )
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults())
        .getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(authenticationProvider());
        
    return http.build();
		
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(uds);
		authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authenticationProvider;
	}	
}
